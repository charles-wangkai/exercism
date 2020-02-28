#include "meetup.h"

namespace meetup
{
scheduler::scheduler(boost::date_time::months_of_year month, int year) : month{month}, year{year} {}

boost::gregorian::date scheduler::find_date(boost::date_time::weekdays weekday, Type type) const
{
    boost::gregorian::date d{year, month, 1};
    int count{0};
    boost::gregorian::date last;

    while (d.month() == month)
    {
        if (d.day_of_week() == weekday)
        {
            last = d;
            ++count;

            if ((type == Type::TEENTH && d.day() >= 13 && d.day() <= 19) ||
                (type == Type::FIRST && count == 1) ||
                (type == Type::SECOND && count == 2) ||
                (type == Type::THIRD && count == 3) ||
                (type == Type::FOURTH && count == 4))
            {
                return d;
            }
        }

        d += boost::gregorian::date_duration{1};
    }

    return last;
}

boost::gregorian::date scheduler::monteenth() const { return find_date(boost::date_time::weekdays::Monday, Type::TEENTH); }

boost::gregorian::date scheduler::tuesteenth() const { return find_date(boost::date_time::weekdays::Tuesday, Type::TEENTH); }

boost::gregorian::date scheduler::wednesteenth() const { return find_date(boost::date_time::weekdays::Wednesday, Type::TEENTH); }

boost::gregorian::date scheduler::thursteenth() const { return find_date(boost::date_time::weekdays::Thursday, Type::TEENTH); }

boost::gregorian::date scheduler::friteenth() const { return find_date(boost::date_time::weekdays::Friday, Type::TEENTH); }

boost::gregorian::date scheduler::saturteenth() const { return find_date(boost::date_time::weekdays::Saturday, Type::TEENTH); }

boost::gregorian::date scheduler::sunteenth() const { return find_date(boost::date_time::weekdays::Sunday, Type::TEENTH); }

boost::gregorian::date scheduler::first_monday() const { return find_date(boost::date_time::weekdays::Monday, Type::FIRST); }

boost::gregorian::date scheduler::first_tuesday() const { return find_date(boost::date_time::weekdays::Tuesday, Type::FIRST); }

boost::gregorian::date scheduler::first_wednesday() const { return find_date(boost::date_time::weekdays::Wednesday, Type::FIRST); }

boost::gregorian::date scheduler::first_thursday() const { return find_date(boost::date_time::weekdays::Thursday, Type::FIRST); }

boost::gregorian::date scheduler::first_friday() const { return find_date(boost::date_time::weekdays::Friday, Type::FIRST); }

boost::gregorian::date scheduler::first_saturday() const { return find_date(boost::date_time::weekdays::Saturday, Type::FIRST); }

boost::gregorian::date scheduler::first_sunday() const { return find_date(boost::date_time::weekdays::Sunday, Type::FIRST); }

boost::gregorian::date scheduler::second_monday() const { return find_date(boost::date_time::weekdays::Monday, Type::SECOND); }

boost::gregorian::date scheduler::second_tuesday() const { return find_date(boost::date_time::weekdays::Tuesday, Type::SECOND); }

boost::gregorian::date scheduler::second_wednesday() const { return find_date(boost::date_time::weekdays::Wednesday, Type::SECOND); }

boost::gregorian::date scheduler::second_thursday() const { return find_date(boost::date_time::weekdays::Thursday, Type::SECOND); }

boost::gregorian::date scheduler::second_friday() const { return find_date(boost::date_time::weekdays::Friday, Type::SECOND); }

boost::gregorian::date scheduler::second_saturday() const { return find_date(boost::date_time::weekdays::Saturday, Type::SECOND); }

boost::gregorian::date scheduler::second_sunday() const { return find_date(boost::date_time::weekdays::Sunday, Type::SECOND); }

boost::gregorian::date scheduler::third_monday() const { return find_date(boost::date_time::weekdays::Monday, Type::THIRD); }

boost::gregorian::date scheduler::third_tuesday() const { return find_date(boost::date_time::weekdays::Tuesday, Type::THIRD); }

boost::gregorian::date scheduler::third_wednesday() const { return find_date(boost::date_time::weekdays::Wednesday, Type::THIRD); }

boost::gregorian::date scheduler::third_thursday() const { return find_date(boost::date_time::weekdays::Thursday, Type::THIRD); }

boost::gregorian::date scheduler::third_friday() const { return find_date(boost::date_time::weekdays::Friday, Type::THIRD); }

boost::gregorian::date scheduler::third_saturday() const { return find_date(boost::date_time::weekdays::Saturday, Type::THIRD); }

boost::gregorian::date scheduler::third_sunday() const { return find_date(boost::date_time::weekdays::Sunday, Type::THIRD); }

boost::gregorian::date scheduler::fourth_monday() const { return find_date(boost::date_time::weekdays::Monday, Type::FOURTH); }

boost::gregorian::date scheduler::fourth_tuesday() const { return find_date(boost::date_time::weekdays::Tuesday, Type::FOURTH); }

boost::gregorian::date scheduler::fourth_wednesday() const { return find_date(boost::date_time::weekdays::Wednesday, Type::FOURTH); }

boost::gregorian::date scheduler::fourth_thursday() const { return find_date(boost::date_time::weekdays::Thursday, Type::FOURTH); }

boost::gregorian::date scheduler::fourth_friday() const { return find_date(boost::date_time::weekdays::Friday, Type::FOURTH); }

boost::gregorian::date scheduler::fourth_saturday() const { return find_date(boost::date_time::weekdays::Saturday, Type::FOURTH); }

boost::gregorian::date scheduler::fourth_sunday() const { return find_date(boost::date_time::weekdays::Sunday, Type::FOURTH); }

boost::gregorian::date scheduler::last_monday() const { return find_date(boost::date_time::weekdays::Monday, Type::LAST); }

boost::gregorian::date scheduler::last_tuesday() const { return find_date(boost::date_time::weekdays::Tuesday, Type::LAST); }

boost::gregorian::date scheduler::last_wednesday() const { return find_date(boost::date_time::weekdays::Wednesday, Type::LAST); }

boost::gregorian::date scheduler::last_thursday() const { return find_date(boost::date_time::weekdays::Thursday, Type::LAST); }

boost::gregorian::date scheduler::last_friday() const { return find_date(boost::date_time::weekdays::Friday, Type::LAST); }

boost::gregorian::date scheduler::last_saturday() const { return find_date(boost::date_time::weekdays::Saturday, Type::LAST); }

boost::gregorian::date scheduler::last_sunday() const { return find_date(boost::date_time::weekdays::Sunday, Type::LAST); }
} // namespace meetup
