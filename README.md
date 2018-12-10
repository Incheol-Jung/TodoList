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

### Environment
- BackEnd : SpringBoot
- FrontEnd : html, JSGrid(Javascript)
- DB : h2 inmemory DB

### DB Schema

### API Specification

Url : localhost:29801/todos <br />
Method : GET <br />
Description : Get Todos <br />
Request : 
```
{
	"pageSize": 10,
	"pageIndex": 1,
	"task": "study",
	"createdDate": "2018-05-45 12:00:00",
	"updatedDate": "2018-05-45 12:00:00"
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
                    "createdDate": "2018-05-45 12:00:00",
                    "updatedDate": "2018-05-45 12:00:00",
                    "isDone": true,
                    "referenceIds": [1,2,3,4]
              },
              {
                    "todoId": 27,
                    "task": "study angular",
                    "createdDate": "2018-05-45 12:00:00",
                    "updatedDate": null,
                    "isDone": false,
                    "referenceIds": [1]
              }
    ]
}
```

Url : localhost:29801/todos <br />
Method : POST <br />
Description : Insert todo <br />
Request : 
```
{
	"todoId": 26,
        "task": "study java",
        "createdDate": "2018-05-45 12:00:00",
        "updatedDate": "2018-05-45 12:00:00",
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

Url : localhost:29801/todos/{todoId} <br />
Method : PUT <br />
Description : Update todo <br />
Request : 
```
{
	"todoId": 26,
        "task": "study java",
        "createdDate": "2018-05-45 12:00:00",
        "updatedDate": "2018-05-45 12:00:00",
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

Url : localhost:29801/todos/{todoId} <br />
Method : DELETE <br />
Description : Delete todo <br />
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






