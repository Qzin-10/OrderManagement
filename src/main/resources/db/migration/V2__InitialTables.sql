CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,                -- Auto-incrementing primary key
                       user_uuid VARCHAR(255) NOT NULL ,  -- Generates a unique UUID
                       username VARCHAR(255) NOT NULL,            -- Username field
                       email VARCHAR(255) UNIQUE NOT NULL,        -- Email field, must be unique
                       contact_number VARCHAR(15),                -- Contact number
                       is_active BOOLEAN DEFAULT TRUE,            -- Indicates if the user is active
                       created_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the user is created
                       updated_at TIMESTAMP DEFAULT NOW()         -- Timestamp for when the user is updated
);

CREATE TABLE address (
                         address_id SERIAL PRIMARY KEY,             -- Auto-incrementing primary key
                         user_id INT NOT NULL,                      -- Foreign key reference to users
                         door_no VARCHAR(50),                       -- Door number
                         house_no VARCHAR(50),                      -- House number
                         street_name VARCHAR(255),                  -- Street name
                         locality VARCHAR(255),                     -- Locality
                         landmark VARCHAR(255),                     -- Landmark
                         pincode VARCHAR(10),                       -- PIN code
                         city VARCHAR(100),                         -- City name
                         is_default BOOLEAN DEFAULT FALSE,          -- Indicates if this is the default address
                         created_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the address is created
                         updated_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the address is updated
                         FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE -- Cascade delete
);

CREATE TABLE kitchen (
                         kitchen_id SERIAL PRIMARY KEY,             -- Auto-incrementing primary key
                         kitchen_uuid VARCHAR(50) NOT NULL , -- Unique UUID for each kitchen
                         name VARCHAR(255) NOT NULL,                -- General name
                         address TEXT,                              -- Full address
                         city VARCHAR(100),                         -- City
                         state VARCHAR(100),                        -- State
                         postal_code VARCHAR(20),                   -- Postal code
                         country VARCHAR(100),                      -- Country
                         latitude DOUBLE PRECISION,                 -- Latitude for geolocation
                         longitude DOUBLE PRECISION,                -- Longitude for geolocation
                         contact_number VARCHAR(15),                -- Contact number
                         email VARCHAR(255),                        -- Email address
                         is_active BOOLEAN DEFAULT TRUE,            -- Indicates if the kitchen is active
                         manager_name VARCHAR(255),                 -- Manager's name
                         manager_contact VARCHAR(15),               -- Manager's contact number
                         created_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the kitchen is created
                         updated_at TIMESTAMP DEFAULT NOW()         -- Timestamp for when the kitchen is updated
);

CREATE TABLE discount (
                          discount_id SERIAL PRIMARY KEY,              -- Auto-incrementing primary key
                          discount_type VARCHAR(50) NOT NULL,          -- Type of discount (e.g., "Percentage", "Flat Amount")
                          discount_value DOUBLE PRECISION NOT NULL,    -- Value of the discount (e.g., 20% or 50 units)
                          is_active BOOLEAN DEFAULT TRUE,              -- Indicates if the discount is currently active
                          updated_time TIMESTAMP DEFAULT NOW(),        -- Last update time for the discount
                          minimum_value DOUBLE PRECISION NOT NULL,     -- Minimum value required for discount applicability
                          created_at TIMESTAMP DEFAULT NOW()           -- Timestamp for when the discount is created
);

CREATE TABLE menu (
                      menu_id SERIAL PRIMARY KEY,                -- Auto-incrementing primary key
                      menu_uuid VARCHAR(255) NOT NULL, -- Unique UUID for each kitchen
                      kitchen_id INT UNIQUE NOT NULL,            -- Foreign key reference to kitchen (One-to-One)
                      created_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the menu is created
                      updated_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the menu is updated
                      FOREIGN KEY (kitchen_id) REFERENCES kitchen(kitchen_id) ON DELETE CASCADE -- Cascade delete
);

CREATE TABLE itemmeta (
                          itemmeta_id SERIAL PRIMARY KEY,             -- Auto-incrementing primary key
                          itemmeta_uuid VARCHAR(255) NOT NULL,
                          name VARCHAR(255) NOT NULL,                 -- Name of the item (indexed for faster search)
                          description TEXT,                           -- Description of the item
                          image_url TEXT,                             -- URL for the image
                          video_url TEXT,                             -- URL for the video
                          veg_status BOOLEAN DEFAULT TRUE,            -- Indicates if the item is vegetarian
                          category TEXT NOT NULL,                   -- Foreign key to the Category table
                          created_at TIMESTAMP DEFAULT NOW(),         -- Timestamp for when the metadata is created
                          updated_at TIMESTAMP DEFAULT NOW()        -- Timestamp for when the metadata is updated
);

CREATE TABLE item (
                      item_id SERIAL PRIMARY KEY,                -- Auto-incrementing primary key
                      item_uuid VARCHAR(100) NOT NULL ,  -- Unique UUID for the item
                      itemmeta_id INT NOT NULL,
                      menu_id INT NOT NULL , -- Foreign key reference to ItemMeta
                      price DOUBLE PRECISION NOT NULL,           -- Price of the item
                      is_available BOOLEAN DEFAULT TRUE,         -- Indicates if the item is available
                      serving_quantity VARCHAR(100) NOT NULL,    -- Serving quantity (e.g., "1 Plate", "500 ml")
                      discount_id INT UNIQUE,                    -- One-to-One foreign key to Discount
                      created_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the item is created
                      updated_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the item is updated
                      FOREIGN KEY (itemmeta_id) REFERENCES itemmeta(itemmeta_id) ON DELETE CASCADE, -- Cascade delete for metadata
                      FOREIGN KEY (discount_id) REFERENCES discount(discount_id) ON DELETE SET NULL,
                      FOREIGN KEY (menu_id) REFERENCES menu(menu_id)
);

CREATE TABLE cart (
                      cart_id SERIAL PRIMARY KEY,                -- Auto-incrementing primary key
                      user_id INT NOT NULL,                      -- Foreign key to the user table
                      is_active BOOLEAN DEFAULT TRUE,            -- Indicates if the cart is active
                      created_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the cart is created
                      updated_at TIMESTAMP DEFAULT NOW(),        -- Timestamp for when the cart is updated
                      FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE -- Cascade delete on user deletion
);


CREATE TABLE orders (
                        order_id UUID DEFAULT gen_random_uuid() PRIMARY KEY, -- Unique identifier for the order
                        user_id INT NOT NULL,                               -- Foreign key reference to user
                        kitchen_id INT NOT NULL,                            -- Foreign key reference to kitchen
                        order_value DOUBLE PRECISION NOT NULL,             -- Total value of the order
                        payment_method VARCHAR(50) NOT NULL,               -- Payment method (e.g., "Cash", "Card", "UPI")
                        delivery_address TEXT NOT NULL,                    -- Address where the order should be delivered
                        contact_number VARCHAR(15) NOT NULL,               -- Contact number for the customer
                        customer_name VARCHAR(255) NOT NULL,               -- Name of the customer
                        order_status VARCHAR(50) NOT NULL DEFAULT 'Pending', -- Status of the order (e.g., "Pending", "Delivered")
                        cart_id INT,                                       -- Foreign key reference to cart
                        created_at TIMESTAMP DEFAULT NOW(),                -- Timestamp for when the order is created
                        updated_at TIMESTAMP DEFAULT NOW(),                -- Timestamp for when the order is updated
                        FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE, -- Cascade delete on user deletion
                        FOREIGN KEY (kitchen_id) REFERENCES kitchen(kitchen_id) ON DELETE CASCADE, -- Cascade delete on kitchen deletion
                        FOREIGN KEY (cart_id) REFERENCES cart(cart_id) ON DELETE SET NULL -- Retain order if cart is deleted
);

CREATE TABLE payments (
                          payment_id SERIAL PRIMARY KEY,             -- Auto-incrementing primary key
                          user_id INT NOT NULL,                      -- Foreign key linking to the user
                          order_id UUID NOT NULL,                    -- Foreign key linking to the order
                          payment_value DOUBLE PRECISION NOT NULL,   -- Value of the payment
                          payment_method VARCHAR(50) NOT NULL,       -- Payment method (e.g., "Cash", "Card", "UPI")
                          payment_status VARCHAR(50) NOT NULL,       -- Status of the payment (e.g., "Completed", "Pending", "Failed")
                          payment_date TIMESTAMP DEFAULT NOW(),      -- Timestamp for when the payment was made
                          FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE, -- Cascade delete on user deletion
                          FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE -- Cascade delete on order deletion
);

CREATE TABLE cart_items (
                            cart_item_id SERIAL PRIMARY KEY,           -- Auto-incrementing primary key
                            cart_id INT NOT NULL,                      -- Foreign key to the cart table
                            item_id INT NOT NULL,                      -- Foreign key to the item table
                            quantity INT NOT NULL DEFAULT 1,           -- Quantity of the item in the cart
                            created_at TIMESTAMP DEFAULT NOW(),        -- Timestamp when the item was added
                            updated_at TIMESTAMP DEFAULT NOW(),        -- Timestamp when the item was last updated
                            FOREIGN KEY (cart_id) REFERENCES cart(cart_id) ON DELETE CASCADE,  -- Cascade delete on cart deletion
                            FOREIGN KEY (item_id) REFERENCES item(item_id) ON DELETE CASCADE  -- Cascade delete on item deletion
);






