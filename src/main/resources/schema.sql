CREATE TABLE IF NOT EXISTS todo(
  todoId      INT PRIMARY KEY AUTO_INCREMENT,
  task			VARCHAR(50),
  createdDate	TIMESTAMP,
  updatedDate	TIMESTAMP,
  isDone		bit
);

CREATE TABLE IF NOT EXISTS mapTodo(
  mapId      INT PRIMARY KEY AUTO_INCREMENT,
  todoId      	INT,
  referenceId	INT
);