USE MY_BOOK_LIST;

INSERT INTO Author (Name, Surname) VALUES ('John', 'Doe');
INSERT INTO Author (Name, Surname) VALUES ('Jane', 'Smith');
INSERT INTO Author (Name, Surname) VALUES ('Michael', 'Johnson');





INSERT INTO Personal_information (DNI, Author_id, Nationality, Date_of_Birth, Phone, Address)
VALUES ('123456789A', 1, 'American', '1990-05-15', 121212212, '123 Main St');











INSERT INTO Publishing (Name, Email, Country) VALUES ('Penguin Books', 'info@penguinbooks.com', 'United Kingdom');
INSERT INTO Publishing (Name, Email, Country) VALUES ('Random House', 'contact@randomhouse.com', 'United States');
INSERT INTO Publishing (Name, Email, Country) VALUES ('HarperCollins', 'contact@harpercollins.com', 'United States');









INSERT INTO Genre (Descripcion) VALUES ('Fiction');
INSERT INTO Genre (Descripcion) VALUES ('Non-fiction');
INSERT INTO Genre (Descripcion) VALUES ('Science fiction');
INSERT INTO Genre (Descripcion) VALUES ('Romance');
INSERT INTO Genre (Descripcion) VALUES ('Mystery');








INSERT INTO Book_read (Name, Publication_date, Pages, Description, Author_id, Publishing_id, Genre_Code)
VALUES ('The Mystery of the Old Mansion', '2023-01-15', 300, 'A thrilling mystery novel', 1, 1, 5);

INSERT INTO Book_read (Name, Publication_date, Pages, Description, Author_id, Publishing_id, Genre_Code)
VALUES ('Love Beyond the Horizon', '2022-08-20', 400, 'A romantic tale of love and loss', 2, 2, 4);

INSERT INTO Book_read (Name, Publication_date, Pages, Description, Author_id, Publishing_id, Genre_Code)
VALUES ('Voyage to Andromeda', '2023-05-10', 250, 'An exploration of the cosmos', 3, 3, 3);



INSERT INTO Book_pending (Name, Publication_date, Pages, Description, Author_id, Publishing_id, Genre_Code)
VALUES ('The Quantum Paradox', '2024-02-28', 350, 'A gripping science fiction adventure', 1, 2, 3);

INSERT INTO Book_pending (Name, Publication_date, Pages, Description, Author_id, Publishing_id, Genre_Code)
VALUES ('The Lost Kingdom', '2024-01-10', 200, 'A historical fiction set in medieval Europe', 2, 3, 1);

INSERT INTO Book_pending (Name, Publication_date, Pages, Description, Author_id, Publishing_id, Genre_Code)
VALUES ('The Power of Now', '2024-03-01', 280, 'A self-help book on personal development', 3, 1, 2);
