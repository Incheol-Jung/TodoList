CREATE TABLE todo(
  todoId      INT PRIMARY KEY AUTO_INCREMENT,
  task			VARCHAR(50),
  createdDate	TIMESTAMP,
  updatedDate	TIMESTAMP,
  isDone		bit
);

CREATE TABLE mapTodo(
  mapId      INT PRIMARY KEY AUTO_INCREMENT,
  todoId      	INT,
  referenceId	INT
);