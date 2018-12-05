CREATE TABLE todo(
  id      INT PRIMARY KEY,
  task	VARCHAR(50),
  createdOn	TIMESTAMP,
  updatedOn	TIMESTAMP,
  isDone		bit
);

CREATE TABLE mapTodo(
  id      INT PRIMARY KEY AUTO_INCREMENT,
  taskId      	INT,
  referenceId	INT
);