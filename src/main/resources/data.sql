INSERT INTO todo(task, createdDate, updatedDate, isDone) 
				values('study',null,null,true)
					,('java',null,null,false)
                    ,('c#',null,null,false)
                    ,('java script',null,null,false);
                    
INSERT INTO mapTodo(todoId, referenceId) 
				values(1,2)
					,(1,3)
					,(1,4)
					,(3,2);