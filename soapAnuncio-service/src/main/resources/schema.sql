


CREATE TABLE IF NOT EXISTS anuncio (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       titulo VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    imagen_url VARCHAR(255),
    fecha_inicio DATE,
    fecha_fin DATE,
    estado BOOLEAN DEFAULT TRUE
    );


INSERT INTO anuncio
(titulo, descripcion, imagen_url, fecha_inicio, fecha_fin, estado)
VALUES
    (
        'Promoción de Vitaminas',
        '20% de descuento en todas las vitaminas.',
        'https://misitio.com/img/vitaminas.jpg',
        '2026-06-01',
        '2026-06-30',
        1
    ),
    (
        'Campaña de Vacunación',
        'Vacunación gratuita para adultos mayores.',
        'https://misitio.com/img/vacunas.jpg',
        '2026-06-01',
        '2026-07-15',
        1
    );