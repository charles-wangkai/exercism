class Cell(object):
    def __init__(self, value=None, dependencies=set(), updater_callback=None):
        self.observers = set()
        self.watcher_callbacks = set()

        self.dependencies = dependencies
        self.updater_callback = updater_callback
        self.set_value(value)

        if dependencies:
            for dependency in dependencies:
                dependency.observers.add(self)
            self.notify()

    def set_value(self, value):
        self.value = value

        for watcher_callback in self.watcher_callbacks:
            watcher_callback(self, value)

        if not self.dependencies:
            followers = [self]
            i = 0
            while i < len(followers):
                for observer in followers[i].observers:
                    if observer not in followers:
                        followers.append(observer)
                i += 1

            for follower in followers:
                if follower != self:
                    follower.notify()

    def add_watcher(self, watcher_callback):
        self.watcher_callbacks.add(watcher_callback)

    def remove_watcher(self, watcher_callback):
        self.watcher_callbacks.remove(watcher_callback)

    def notify(self):
        new_value = self.updater_callback(
            [dependency.value for dependency in self.dependencies])

        if new_value != self.value:
            self.set_value(new_value)


class Reactor(object):
    def create_input_cell(self, value):
        return Cell(value=value)

    def create_compute_cell(self, dependencies, updater_callback):
        return Cell(dependencies=dependencies, updater_callback=updater_callback)
