import json


class RestAPI:
    def __init__(self, database=None):
        self.user_to_ledger = {}
        if database:
            users = database['users']
            for user in users:
                self.user_to_ledger[user['name']] = {}
            for user in users:
                for other in user['owes']:
                    self.update_record(
                        other, user['name'], user['owes'][other])

    def get(self, url, payload=None):
        if url == '/users':
            if payload:
                users = sorted(json.loads(payload)['users'])
            else:
                users = sorted(self.user_to_ledger)

            return json.dumps({'users': [self.build_user_output(user) for user in users]})

    def post(self, url, payload=None):
        if url == '/add':
            user = json.loads(payload)['user']
            self.user_to_ledger[user] = {}

            return json.dumps(self.build_user_output(user))
        elif url == '/iou':
            d = json.loads(payload)
            self.update_record(d['lender'], d['borrower'], d['amount'])

            return json.dumps({'users': [self.build_user_output(user) for user in sorted([d['lender'], d['borrower']])]})

    def update_record(self, lender, borrower, amount):
        self.user_to_ledger[lender][borrower] = self.user_to_ledger[lender].get(
            borrower, 0) + amount
        if not self.user_to_ledger[lender][borrower]:
            del self.user_to_ledger[lender][borrower]

        self.user_to_ledger[borrower][lender] = self.user_to_ledger[borrower].get(
            lender, 0) - amount
        if not self.user_to_ledger[borrower][lender]:
            del self.user_to_ledger[borrower][lender]

    def build_user_output(self, user):
        return {
            'name': user,
            'owes': {other: -self.user_to_ledger[user][other] for other in self.user_to_ledger[user] if self.user_to_ledger[user][other] < 0},
            'owed_by': {other: self.user_to_ledger[user][other] for other in self.user_to_ledger[user] if self.user_to_ledger[user][other] > 0},
            'balance': sum(self.user_to_ledger[user].values())
        }
