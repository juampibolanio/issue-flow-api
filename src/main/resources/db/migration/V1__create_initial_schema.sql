CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    role VARCHAR(50) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT chk_users_role CHECK ( role IN ('EMPLOYEE', 'DEVELOPER') )
);

CREATE TABLE tickets (
    id UUID PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    description VARCHAR,
    state VARCHAR(30) NOT NULL DEFAULT 'PENDING',
    priority VARCHAR(30) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    assigned_id UUID,
    reporter_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,

    CONSTRAINT fk_assigned_user FOREIGN KEY (assigned_id) REFERENCES users(id),
    CONSTRAINT fk_reporter_user FOREIGN KEY (reporter_id) REFERENCES users(id),

    CONSTRAINT chk_tickets_state CHECK ( state IN ('PENDING', 'IN_PROGRESS', 'CLOSED') ),
    CONSTRAINT chk_tickets_priority CHECK ( priority IN ('URGENT', 'HIGH', 'MEDIUM', 'LOW'))
);