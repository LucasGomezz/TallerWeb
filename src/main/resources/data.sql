INSERT INTO usuario (id,email,password,rol,activo, dinero) VALUES(1, 'test@unlam.edu.ar', 'test', 'ADMIN', true, 1000);

INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Jason Tatum", 71, 73, 75, 77, 80, 84);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("AL Horford", 70, 72, 76, 78, 81, 83);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Lonzo Ball", 70, 73, 74, 77, 82, 85);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Andre Drummond", 71, 72, 76, 79, 80, 84);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Sharife Cooper", 70, 72, 75, 78, 81, 85);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Tristan Thompson", 70, 74, 75, 77, 80, 84);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Russell Westbrook", 71, 73, 76, 78, 82, 85);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Kawhi Leonard", 71, 72, 75, 79, 80, 83);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Donte DiVincenzo", 70, 73, 75, 79, 81, 84);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Josh Hart", 70, 72, 74, 77, 82, 85);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("LeBron James", 71, 73, 74, 76, 84, 84);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Anthony Davis", 71, 74, 76, 77, 80, 83);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Tyler Herro", 70, 73, 76, 78, 82, 84);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Duncan Robinson", 70, 71, 75, 79, 80, 85);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Luka Doncic", 71, 73, 77, 79, 81, 84);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Dwight Powell", 71, 72, 74, 77, 82, 84);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Damian Lillard", 70, 74, 75, 79, 81, 83);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Giannis Antetokounmpo", 70, 71, 76, 78, 84, 85);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Stephen Curry", 70, 85, 76, 77, 81, 78);
INSERT INTO jugador(nombre, drible, tiro, pase, robo, tapa, intercepcion)VALUES("Klay Thompson", 71, 84, 75, 76, 80, 84);

INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("Boston Celtics", 1, 2,"images/logoEquipos/celtics.png", "verde");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("Chicago Bulls", 3, 4,"images/logoEquipos/bulls.png", "rojo");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("Cleveland Cavaliers", 5, 6,"images/logoEquipos/cleveland.png", "rojo");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("LA Clippers", 7, 8,"images/logoEquipos/clippers.png", "azul");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("New York Knicks", 9, 10,"images/logoEquipos/knicks.png", "naranja");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("Los Angeles Lakers", 11, 12,"images/logoEquipos/lakers.png", "amarillo");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("Miami Heat", 13, 14,"images/logoEquipos/miami.png", "rojo");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("Dallas Mavericks", 15, 16,"images/logoEquipos/dallas.png", "azul");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("Milwaukee Bucks", 17, 18,"images/logoEquipos/bucks.png", "verde");
INSERT INTO equipo(nombre, jugador1_id, jugador2_id, logo, color)VALUES("Golden State Warriors", 19, 20,"images/logoEquipos/warriors.png", "azul");

INSERT INTO categoria(nombre, porcentaje)VALUES("Tirar", 80);
INSERT INTO categoria(nombre, porcentaje)VALUES("Pasar", 30);
INSERT INTO categoria(nombre, porcentaje)VALUES("Driblear", 55);
INSERT INTO categoria(nombre, porcentaje)VALUES("Robar", 20);
INSERT INTO categoria(nombre, porcentaje)VALUES("Correr", 12);
INSERT INTO categoria(nombre, porcentaje)VALUES("Esquivar", 30);


INSERT INTO productoTienda(nombre, precio, imagen, categoria)VALUES("gaytorade", 120, "images/ITEM.png", (SELECT id FROM Categoria WHERE nombre = "Tirar"));
INSERT INTO productoTienda(nombre, precio, imagen, categoria)VALUES("Mr. Basquet", 230, "images/ITEM.png", (SELECT id FROM Categoria WHERE nombre = "Pasar"));
INSERT INTO productoTienda(nombre, precio, imagen, categoria)VALUES("Pachu", 180, "images/ITEM.png", (SELECT id FROM Categoria WHERE nombre = "Driblear"));
INSERT INTO productoTienda(nombre, precio, imagen, categoria)VALUES("Libertador", 300, "images/ITEM.png", (SELECT id FROM Categoria WHERE nombre = "Robar"));
INSERT INTO productoTienda(nombre, precio, imagen, categoria)VALUES("Goodsilla", 220, "images/ITEM.png", (SELECT id FROM Categoria WHERE nombre = "Correr"));
INSERT INTO productoTienda(nombre, precio, imagen, categoria)VALUES("Esquivar", 280, "images/ITEM.png", (SELECT id FROM Categoria WHERE nombre = "Esquivar"));
