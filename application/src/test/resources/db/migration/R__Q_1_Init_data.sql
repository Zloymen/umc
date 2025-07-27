insert into VENUE(brand, provider, external_id, reference_id, name)
    values('brand1', 'provider', 'id', 'venue_1', 'Venue1'),
          ('brand2', 'provider', 'id', 'venue_2', 'Venue2'),
          ('brand3', 'provider', 'id', 'venue_3', 'Venue3'),
          ('brand4', 'provider', 'id', 'venue_4', 'Venue4'),
          ('brand5', 'provider', 'id', 'venue_5', 'Venue5'),
          ('brand6', 'provider', 'id', 'venue_6', 'Venue6'),
          ('brand7', 'provider', 'id', 'venue_7', 'Venue7'),
          ('brand8', 'provider', 'id', 'venue_8', 'Venue8');

insert into EVENT(END_TIME, NAME, START_TIME)
    values ('2025-07-25 22:08:02.000000', 'event1', '2025-07-25 21:08:10.000000'),
           ('2025-07-26 22:08:02.000000', 'event2', '2025-07-26 21:08:10.000000'),
           ('2025-07-27 22:08:02.000000', 'event3', '2025-07-27 21:08:10.000000'),
           ('2025-07-28 22:08:02.000000', 'event4', '2025-07-28 21:08:10.000000'),
           ('2025-07-29 22:08:02.000000', 'event5', '2025-07-29 21:08:10.000000'),
           ('2025-07-30 22:08:02.000000', 'event6', '2025-07-30 21:08:10.000000'),
           ('2025-07-31 22:08:02.000000', 'event7', '2025-07-31 21:08:10.000000'),
           ('2025-08-01 22:08:02.000000', 'event8', '2025-08-01 21:08:10.000000'),
           ('2025-08-02 22:08:02.000000', 'event9', '2025-08-02 21:08:10.000000');

insert into EVENT_VENUE (EVENT_ID, VENUE_BRAND, VENUE_EXTERNAL_ID, VENUE_PROVIDER)
select (select id from EVENT where name = 'event1'), brand, external_id, provider
from VENUE where brand in ('brand1', 'brand4', 'brand6', 'brand8');

insert into EVENT_VENUE (EVENT_ID, VENUE_BRAND, VENUE_EXTERNAL_ID, VENUE_PROVIDER)
select (select id from EVENT where name = 'event2'), brand,external_id, provider
from VENUE where brand in ('brand1', 'brand4', 'brand6', 'brand8');

insert into EVENT_VENUE (EVENT_ID, VENUE_BRAND, VENUE_EXTERNAL_ID, VENUE_PROVIDER)
select (select id from EVENT where name = 'event3'), brand, external_id, provider
from VENUE where brand in ('brand1', 'brand4', 'brand6', 'brand8');

insert into EVENT_VENUE (EVENT_ID, VENUE_BRAND, VENUE_EXTERNAL_ID, VENUE_PROVIDER)
select (select id from EVENT where name = 'event4'), brand, external_id, provider
from VENUE where brand in ('brand2', 'brand3', 'brand5', 'brand7');

insert into EVENT_VENUE (EVENT_ID, VENUE_BRAND, VENUE_EXTERNAL_ID, VENUE_PROVIDER)
select (select id from EVENT where name = 'event5'), brand, external_id, provider
from VENUE where brand in ('brand2', 'brand3', 'brand5', 'brand7');

insert into EVENT_VENUE (EVENT_ID, VENUE_BRAND, VENUE_EXTERNAL_ID, VENUE_PROVIDER)
select (select id from EVENT where name = 'event1'), brand, external_id, provider
from VENUE where brand in ('brand2', 'brand3', 'brand5', 'brand7');

insert into EVENT_VENUE (EVENT_ID, VENUE_BRAND, VENUE_EXTERNAL_ID, VENUE_PROVIDER)
select (select id from EVENT where name = 'event3'), brand, external_id, provider
from VENUE where brand in ('brand2', 'brand3', 'brand5', 'brand7');