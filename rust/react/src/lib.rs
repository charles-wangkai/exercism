use std::collections::{HashMap, HashSet};

/// `InputCellId` is a unique identifier for an input cell.
#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
pub struct InputCellId(usize);
/// `ComputeCellId` is a unique identifier for a compute cell.
/// Values of type `InputCellId` and `ComputeCellId` should not be mutually assignable,
/// demonstrated by the following tests:
///
/// ```compile_fail
/// let mut r = react::Reactor::new();
/// let input: react::ComputeCellId = r.create_input(111);
/// ```
///
/// ```compile_fail
/// let mut r = react::Reactor::new();
/// let input = r.create_input(111);
/// let compute: react::InputCellId = r.create_compute(&[react::CellId::Input(input)], |_| 222).unwrap();
/// ```
#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
pub struct ComputeCellId(usize);
#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
pub struct CallbackId(usize);

#[derive(Clone, Copy, Debug, PartialEq, Eq, Hash)]
pub enum CellId {
    Input(InputCellId),
    Compute(ComputeCellId),
}

#[derive(Debug, PartialEq, Eq)]
pub enum RemoveCallbackError {
    NonexistentCell,
    NonexistentCallback,
}

type ComputeFunc<'a, T> = Box<dyn 'a + Fn(&[T]) -> T>;

pub struct Reactor<'a, T> {
    cell_id_to_observers: HashMap<CellId, HashSet<ComputeCellId>>,
    input_cell_id_to_value: HashMap<InputCellId, T>,
    compute_cell_id_to_dependencies: HashMap<ComputeCellId, Vec<CellId>>,
    compute_cell_id_to_compute_func: HashMap<ComputeCellId, ComputeFunc<'a, T>>,
    next_callback_id: usize,
    compute_cell_id_to_callback_ids: HashMap<ComputeCellId, HashSet<CallbackId>>,
    callback_id_to_compute_cell_id: HashMap<CallbackId, ComputeCellId>,
    callback_id_to_callback: HashMap<CallbackId, Box<dyn 'a + FnMut(T)>>,
}

// You are guaranteed that Reactor will only be tested against types that are Copy + PartialEq.
impl<'a, T: Copy + PartialEq> Reactor<'a, T> {
    pub fn new() -> Self {
        Self {
            cell_id_to_observers: HashMap::new(),
            input_cell_id_to_value: HashMap::new(),
            compute_cell_id_to_dependencies: HashMap::new(),
            compute_cell_id_to_compute_func: HashMap::new(),
            next_callback_id: 0,
            compute_cell_id_to_callback_ids: HashMap::new(),
            callback_id_to_compute_cell_id: HashMap::new(),
            callback_id_to_callback: HashMap::new(),
        }
    }

    // Creates an input cell with the specified initial value, returning its ID.
    pub fn create_input(&mut self, initial: T) -> InputCellId {
        let input_cell_id = InputCellId(self.input_cell_id_to_value.len());
        self.cell_id_to_observers
            .insert(CellId::Input(input_cell_id), HashSet::new());
        self.set_value(input_cell_id, initial);

        input_cell_id
    }

    // Creates a compute cell with the specified dependencies and compute function.
    // The compute function is expected to take in its arguments in the same order as specified in
    // `dependencies`.
    // You do not need to reject compute functions that expect more arguments than there are
    // dependencies (how would you check for this, anyway?).
    //
    // If any dependency doesn't exist, returns an Err with that nonexistent dependency.
    // (If multiple dependencies do not exist, exactly which one is returned is not defined and
    // will not be tested)
    //
    // Notice that there is no way to *remove* a cell.
    // This means that you may assume, without checking, that if the dependencies exist at creation
    // time they will continue to exist as long as the Reactor exists.
    pub fn create_compute<F: 'a + Fn(&[T]) -> T>(
        &mut self,
        dependencies: &[CellId],
        compute_func: F,
    ) -> Result<ComputeCellId, CellId> {
        if let Some(&missing_cell_id) = dependencies.iter().find(|dependency| match dependency {
            CellId::Input(input_cell_id) => {
                !self.input_cell_id_to_value.contains_key(input_cell_id)
            }
            CellId::Compute(compute_cell_id) => !self
                .compute_cell_id_to_dependencies
                .contains_key(compute_cell_id),
        }) {
            return Err(missing_cell_id);
        }

        let compute_cell_id = ComputeCellId(self.compute_cell_id_to_dependencies.len());
        let cell_id = CellId::Compute(compute_cell_id);
        self.cell_id_to_observers.insert(cell_id, HashSet::new());
        for dependency in dependencies {
            self.cell_id_to_observers
                .get_mut(dependency)
                .unwrap()
                .insert(compute_cell_id);
        }
        self.compute_cell_id_to_dependencies
            .insert(compute_cell_id, dependencies.to_vec());
        self.compute_cell_id_to_compute_func
            .insert(compute_cell_id, Box::new(compute_func));
        self.compute_cell_id_to_callback_ids
            .insert(compute_cell_id, HashSet::new());

        Ok(compute_cell_id)
    }

    // Retrieves the current value of the cell, or None if the cell does not exist.
    //
    // You may wonder whether it is possible to implement `get(&self, id: CellId) -> Option<&Cell>`
    // and have a `value(&self)` method on `Cell`.
    //
    // It turns out this introduces a significant amount of extra complexity to this exercise.
    // We chose not to cover this here, since this exercise is probably enough work as-is.
    pub fn value(&self, id: CellId) -> Option<T> {
        match id {
            CellId::Input(input_cell_id) => {
                self.input_cell_id_to_value.get(&input_cell_id).copied()
            }
            CellId::Compute(compute_cell_id) => {
                if self
                    .compute_cell_id_to_dependencies
                    .contains_key(&compute_cell_id)
                {
                    Some(self.compute_cell_id_to_compute_func[&compute_cell_id](
                        &self.compute_cell_id_to_dependencies[&compute_cell_id]
                            .iter()
                            .map(|&dependency| self.value(dependency).unwrap())
                            .collect::<Vec<_>>(),
                    ))
                } else {
                    None
                }
            }
        }
    }

    // Sets the value of the specified input cell.
    //
    // Returns false if the cell does not exist.
    //
    // Similarly, you may wonder about `get_mut(&mut self, id: CellId) -> Option<&mut Cell>`, with
    // a `set_value(&mut self, new_value: T)` method on `Cell`.
    //
    // As before, that turned out to add too much extra complexity.
    pub fn set_value(&mut self, id: InputCellId, new_value: T) -> bool {
        if !self.cell_id_to_observers.contains_key(&CellId::Input(id)) {
            return false;
        }

        let mut compute_cell_ids = HashSet::new();
        self.search(&mut compute_cell_ids, CellId::Input(id));

        let compute_cell_id_to_old_value =
            HashMap::<_, _>::from_iter(compute_cell_ids.iter().map(|&compute_cell_id| {
                (
                    compute_cell_id,
                    self.value(CellId::Compute(compute_cell_id)).unwrap(),
                )
            }));

        self.input_cell_id_to_value.insert(id, new_value);

        let compute_cell_id_to_new_value =
            HashMap::<_, _>::from_iter(compute_cell_ids.iter().map(|&compute_cell_id| {
                (
                    compute_cell_id,
                    self.value(CellId::Compute(compute_cell_id)).unwrap(),
                )
            }));

        for compute_cell_id in compute_cell_ids {
            if compute_cell_id_to_new_value[&compute_cell_id]
                != compute_cell_id_to_old_value[&compute_cell_id]
            {
                for callback_id in &self.compute_cell_id_to_callback_ids[&compute_cell_id] {
                    self.callback_id_to_callback.get_mut(callback_id).unwrap()(
                        compute_cell_id_to_new_value[&compute_cell_id],
                    );
                }
            }
        }

        true
    }

    fn search(&self, compute_cell_ids: &mut HashSet<ComputeCellId>, id: CellId) {
        for &observer in &self.cell_id_to_observers[&id] {
            if compute_cell_ids.insert(observer) {
                self.search(compute_cell_ids, CellId::Compute(observer));
            }
        }
    }

    // Adds a callback to the specified compute cell.
    //
    // Returns the ID of the just-added callback, or None if the cell doesn't exist.
    //
    // Callbacks on input cells will not be tested.
    //
    // The semantics of callbacks (as will be tested):
    // For a single set_value call, each compute cell's callbacks should each be called:
    // * Zero times if the compute cell's value did not change as a result of the set_value call.
    // * Exactly once if the compute cell's value changed as a result of the set_value call.
    //   The value passed to the callback should be the final value of the compute cell after the
    //   set_value call.
    pub fn add_callback<F: 'a + FnMut(T)>(
        &mut self,
        id: ComputeCellId,
        callback: F,
    ) -> Option<CallbackId> {
        if !self.compute_cell_id_to_dependencies.contains_key(&id) {
            return None;
        }

        let callback_id = CallbackId(self.next_callback_id);
        self.next_callback_id += 1;
        self.compute_cell_id_to_callback_ids
            .get_mut(&id)
            .unwrap()
            .insert(callback_id);
        self.callback_id_to_compute_cell_id.insert(callback_id, id);
        self.callback_id_to_callback
            .insert(callback_id, Box::new(callback));

        Some(callback_id)
    }

    // Removes the specified callback, using an ID returned from add_callback.
    //
    // Returns an Err if either the cell or callback does not exist.
    //
    // A removed callback should no longer be called.
    pub fn remove_callback(
        &mut self,
        cell: ComputeCellId,
        callback: CallbackId,
    ) -> Result<(), RemoveCallbackError> {
        if !self.compute_cell_id_to_callback_ids.contains_key(&cell) {
            return Err(RemoveCallbackError::NonexistentCell);
        }
        if !self.callback_id_to_compute_cell_id.contains_key(&callback) {
            return Err(RemoveCallbackError::NonexistentCallback);
        }

        self.compute_cell_id_to_callback_ids
            .get_mut(&cell)
            .unwrap()
            .remove(&callback);
        self.callback_id_to_compute_cell_id.remove(&callback);
        self.callback_id_to_callback.remove(&callback);

        Ok(())
    }
}

impl<T: Copy + PartialEq> Default for Reactor<'_, T> {
    fn default() -> Self {
        Self::new()
    }
}
