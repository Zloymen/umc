create table EVENT_VENUE
(
    EVENT_ID          BIGINT                 not null,
    VENUE_BRAND       CHARACTER VARYING(255) not null,
    VENUE_EXTERNAL_ID CHARACTER VARYING(255) not null,
    VENUE_PROVIDER    CHARACTER VARYING(255) not null,
    primary key (EVENT_ID, VENUE_BRAND, VENUE_EXTERNAL_ID, VENUE_PROVIDER),
    constraint FK6T7OV7RAO9DCB2VW6RH8SO114
        foreign key (EVENT_ID) references EVENT,
    constraint FKB5SOJQJC4VDT2TYVEAB1G0GRA
        foreign key (VENUE_BRAND, VENUE_PROVIDER, VENUE_EXTERNAL_ID) references VENUE
);
