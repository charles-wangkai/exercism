import datetime


class MeetupDayException(Exception):
    pass


def meetup_day(year, month, day_of_the_week, which):
    weekday = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'].index(day_of_the_week)
    
    d = datetime.date(year, month, 1)
    count = 0
    last = None
    while d.month == month:
        if d.weekday() == weekday:
            last = d
            count += 1
            
            if which == 'teenth':
                if 13 <= d.day <= 20:
                    return d
            elif which != 'last':
                if count == int(which[0]):
                    return d
        d += datetime.timedelta(1)
    
    if which != 'last':
        raise MeetupDayException
    
    return last
