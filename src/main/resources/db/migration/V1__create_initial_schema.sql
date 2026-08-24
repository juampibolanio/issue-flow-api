CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT role CHECK ( role IN ('EMPLOYEE', 'DEVELOPER') ),
);

CREATE TABLE tickets (
    id UUID PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    description VARCHAR,
    state VARCHAR(30) NOT NULL,
    priority VARCHAR(30) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    assigned_id UUID,
    reporter_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_assigned_user FOREIGN KEY (assigned) REFERENCES users(id),
    CONSTRAINT fk_reporter_user FOREIGN KEY (reporter) REFERENCES users(id),

    CONSTRAINT state CHECK ( state IN ('PENDING', 'IN_PROGRESS', 'CLOSED') ),
    CONSTRAINT priority CHECK ( priority IN ('URGENT', 'HIGH', 'MEDIUM', 'LOW')),
);