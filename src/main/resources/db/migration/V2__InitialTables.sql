CREATE TABLE kitchen (
    kitchen_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(15) CHECK (contact_number ~ '^\+?[0-9]{10,15}$'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE inventory (
    item_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    image_url VARCHAR(255),
    video_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE menu (
    menu_id SERIAL PRIMARY KEY,
    kitchen_id INT,
    inventory_item_id INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (kitchen_id, inventory_item_id),
    FOREIGN KEY (kitchen_id) REFERENCES kitchen(kitchen_id),
    FOREIGN KEY (inventory_item_id) REFERENCES inventory(item_id)
);


