## Todo List
Incheol's Todo List

## Installation
```
$ mvn install
```

## Running the Application
```
$ mvn spring-boot:run
```

- Example Url : http://localhost:29801
- Swagger Url : http://localhost:29801/swagger-ui.html

## Strategies

### DB Schema

### API Specification

- Get todo

Url : localhost:29801/todos <br />
Method : GET <br />
Request : 
```
{
	"pageSize": 10,
	"pageIndex": 1,
	"task": "study",
	"createdOn": "2018-05-45 12:00:00",
	"updatedOn": "2018-05-45 12:00:00"
}
```
Response :
```
{
	"success": true,
	"message": null,
	"data": [
              {
                    "todoId": 26,
                    "task": "study java",
                    "createdOn": "2018-05-45 12:00:00",
                    "updatedOn": "2018-05-45 12:00:00",
                    "isDone": true,
                    "referenceIds": [1,2,3,4]
              },
              {
                    "todoId": 27,
                    "task": "study angular",
                    "createdOn": "2018-05-45 12:00:00",
                    "updatedOn": null,
                    "isDone": false,
                    "referenceIds": [1]
              }
    ]
}
```

- Insert todo

Url : localhost:29801/todos <br />
Method : POST <br />
Request : 
```
{
	"todoId": 26,
    "task": "study java",
    "createdOn": "2018-05-45 12:00:00",
    "updatedOn": "2018-05-45 12:00:00",
    "isDone": true,
    	"referenceIds": [1,2,3,4]
}
```
Response :
```
{
	"success": true,
	"message": "Success",
	"data": null
}
```

- Update todo

Url : localhost:29801/todos/{todoId} <br />
Method : PUT <br />
Request : 
```
{
	"todoId": 26,
    "task": "study java",
    "createdOn": "2018-05-45 12:00:00",
    "updatedOn": "2018-05-45 12:00:00",
    "isDone": true,
    	"referenceIds": [1,2,3,4]
}
```
Response :
```
{
	"success": true,
	"message": "Success",
	"data": null
}
```

- Delete todo

Url : localhost:29801/todos/{todoId} <br />
Method : DELETE <br />
Request : 
```

```
Response :
```
{
	"success": true,
	"message": "Success",
	"data": null
}
```






