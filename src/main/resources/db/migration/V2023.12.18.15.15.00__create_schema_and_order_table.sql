CREATE SCHEMA IF NOT EXISTS perches;

DO $$
    BEGIN
        IF
            NOT EXISTS(SELECT 1 FROM pg_catalog.pg_type WHERE typname = 'perches_order_status')
            THEN CREATE TYPE perches.perches_order_status AS ENUM ('ORDER_CREATED', 'ORDER_COMPLETED', 'ORDER_CANCELED');
        END IF;

        IF
            NOT EXISTS(SELECT 1 FROM pg_catalog.pg_type WHERE typname = 'perches_inventory_status')
            THEN CREATE TYPE perches.perches_inventory_status AS ENUM ('INVENTORY_APPROVED', 'INVENTORY_REJECTED');
        END IF;

        IF
            NOT EXISTS(SELECT 1 FROM pg_catalog.pg_type WHERE typname = 'perches_payment_status')
            THEN CREATE TYPE perches.perches_payment_status AS ENUM ('PAYMENT_COMPLETED', 'PAYMENT_REJECTED');
        END IF;
    END
$$;


CREATE TABLE IF NOT EXISTS perches.orders (
    id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    customer_email VARCHAR(150) NOT NULL,
    price INT NOT NULL,
    order_status perches.perches_order_status NOT NULL DEFAULT 'ORDER_CREATED'::perches.perches_order_status,
    inventory_status perches.perches_inventory_status,
    payment_status perches.perches_payment_status,
    constraint order_id_pk PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS perches_order_customer_email on perches.orders (customer_email);
