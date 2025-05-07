CREATE TABLE users
(
    id       INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    role     VARCHAR(32)  NOT NULL DEFAULT 'USER'
);

CREATE TABLE verifications
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    spigotId         INT NOT NULL,
    pluginId         INT NOT NULL,
    verificationTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user
        FOREIGN KEY (spigotId)
            REFERENCES users (id)
            ON DELETE CASCADE
);

CREATE TABLE errors
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    token     VARCHAR(255) NOT NULL,
    message   VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);