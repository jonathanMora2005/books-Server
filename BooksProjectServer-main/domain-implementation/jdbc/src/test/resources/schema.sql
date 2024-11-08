-- PHPMYADMIN SQL DUMP
-- VERSION 5.0.1
-- HTTPS://WWW.PHPMYADMIN.NET/
--
-- HOST: 127.0.0.1
-- GENERATION TIME: MAR 24, 2020 AT 06:55 PM
-- SERVER VERSION: 10.4.11-MARIADB
-- PHP VERSION: 7.4.3
DROP DATABASE IF EXISTS MY_BOOK_LIST;

CREATE DATABASE MY_BOOK_LIST;

USE MY_BOOK_LIST;

CREATE TABLE `author` (
                          `Author_id` int(10) NOT NULL,
                          `Name` varchar(50) DEFAULT NULL,
                          `Surname` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `author`
--

INSERT INTO `author` (`Author_id`, `Name`, `Surname`) VALUES
                                                          (1, 'Updated', 'Updated'),
                                                          (4, 'autho', 'ProbaSurname'),
                                                          (5, 'Michael', 'Johnson'),
                                                          (6, 'Jane', 'Smith'),
                                                          (7, 'john', 'Doe'),
                                                          (8, 'john', 'Doe'),
                                                          (9, 'john', 'Doe'),
                                                          (10, 'john', 'Doe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `book_pending`
--

CREATE TABLE `book_pending` (
                                `ID_book_read` int(10) NOT NULL,
                                `Name` varchar(50) DEFAULT NULL,
                                `Publication_date` date DEFAULT NULL,
                                `Pages` int(10) DEFAULT NULL,
                                `Description` varchar(50) DEFAULT NULL,
                                `Author_id` int(10) DEFAULT NULL,
                                `Publishing_id` int(10) DEFAULT NULL,
                                `Genre_Code` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `book_pending`
--

INSERT INTO `book_pending` (`ID_book_read`, `Name`, `Publication_date`, `Pages`, `Description`, `Author_id`, `Publishing_id`, `Genre_Code`) VALUES
    (1, 'The Quantum Paradox', '2024-02-28', 350, 'A gripping science fiction adventure', 1, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `book_read`
--

CREATE TABLE `book_read` (
                             `ID_book_read` int(10) NOT NULL,
                             `Name` varchar(50) DEFAULT NULL,
                             `Publication_date` date DEFAULT NULL,
                             `Pages` int(10) DEFAULT NULL,
                             `Description` varchar(50) DEFAULT NULL,
                             `Author_id` int(10) DEFAULT NULL,
                             `Publishing_id` int(10) DEFAULT NULL,
                             `Genre_Code` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `book_read` (`ID_book_read`, `Name`, `Publication_date`, `Pages`, `Description`, `Author_id`, `Publishing_id`, `Genre_Code`) VALUES
    (1, 'The Quantum Paradox', '2024-02-28', 350, 'A gripping science fiction adventure', 1, 2, 3);


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genre`
--

CREATE TABLE `genre` (
                         `Code` int(10) NOT NULL,
                         `Descripcion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `genre`
--

INSERT INTO `genre` (`Code`, `Descripcion`) VALUES
                                                (2, 'Updated Genre'),
                                                (3, 'Science fiction'),
                                                (4, 'Romance'),
                                                (6, 'Mystery'),
                                                (7, 'Non-fiction'),
                                                (8, 'Horror');


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal_information`
--

CREATE TABLE `personal_information` (
                                        `DNI` varchar(50) NOT NULL,
                                        `Author_id` int(11) DEFAULT NULL,
                                        `Nationality` varchar(50) DEFAULT NULL,
                                        `Date_of_Birth` varchar(50) DEFAULT NULL,
                                        `Phone` int(10) DEFAULT NULL,
                                        `Address` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personal_information`
--

INSERT INTO `personal_information` (`DNI`, `Author_id`, `Nationality`, `Date_of_Birth`, `Phone`, `Address`) VALUES
    ('123456789A', 1, 'American', '1990-05-15', 121212212, '123 Main St');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publishing`
--

CREATE TABLE `publishing` (
                              `Publishing_id` int(10) NOT NULL,
                              `Name` varchar(50) DEFAULT NULL,
                              `Email` varchar(50) DEFAULT NULL,
                              `Country` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `publishing`
--

INSERT INTO `publishing` (`Publishing_id`, `Name`, `Email`, `Country`) VALUES
                                                                           (1, 'Penguin Books', 'info@penguinbooks.com', 'United Kingdom'),
                                                                           (2, 'Random House', 'contact@randomhouse.com', 'United States');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `author`
--
ALTER TABLE `author`
    ADD PRIMARY KEY (`Author_id`);

--
-- Indices de la tabla `book_pending`
--
ALTER TABLE `book_pending`
    ADD PRIMARY KEY (`ID_book_read`),
    ADD KEY `Author_id` (`Author_id`),
    ADD KEY `Publishing_id` (`Publishing_id`),
    ADD KEY `Genre_Code` (`Genre_Code`);

--
-- Indices de la tabla `book_read`
--
ALTER TABLE `book_read`
    ADD PRIMARY KEY (`ID_book_read`),
    ADD KEY `Author_id` (`Author_id`),
    ADD KEY `Publishing_id` (`Publishing_id`),
    ADD KEY `Genre_Code` (`Genre_Code`);

--
-- Indices de la tabla `genre`
--
ALTER TABLE `genre`
    ADD PRIMARY KEY (`Code`);

--
-- Indices de la tabla `personal_information`
--
ALTER TABLE `personal_information`
    ADD PRIMARY KEY (`DNI`),
    ADD UNIQUE KEY `Author_id` (`Author_id`);

--
-- Indices de la tabla `publishing`
--
ALTER TABLE `publishing`
    ADD PRIMARY KEY (`Publishing_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `author`
--
ALTER TABLE `author`
    MODIFY `Author_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `book_pending`
--
ALTER TABLE `book_pending`
    MODIFY `ID_book_read` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `book_read`
--
ALTER TABLE `book_read`
    MODIFY `ID_book_read` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `genre`
--
ALTER TABLE `genre`
    MODIFY `Code` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `publishing`
--
ALTER TABLE `publishing`
    MODIFY `Publishing_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `book_pending`
--
ALTER TABLE `book_pending`
    ADD CONSTRAINT `book_pending_ibfk_1` FOREIGN KEY (`Author_id`) REFERENCES `author` (`Author_id`),
    ADD CONSTRAINT `book_pending_ibfk_2` FOREIGN KEY (`Publishing_id`) REFERENCES `publishing` (`Publishing_id`),
    ADD CONSTRAINT `book_pending_ibfk_3` FOREIGN KEY (`Genre_Code`) REFERENCES `genre` (`Code`);

--
-- Filtros para la tabla `book_read`
--
ALTER TABLE `book_read`
    ADD CONSTRAINT `book_read_ibfk_1` FOREIGN KEY (`Author_id`) REFERENCES `author` (`Author_id`),
    ADD CONSTRAINT `book_read_ibfk_2` FOREIGN KEY (`Publishing_id`) REFERENCES `publishing` (`Publishing_id`),
    ADD CONSTRAINT `book_read_ibfk_3` FOREIGN KEY (`Genre_Code`) REFERENCES `genre` (`Code`);
