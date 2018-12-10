function search(evt){ 
    if(evt.keyCode == 13) $('#testBtn').click();
}

$(function() {

    

    $("#jsGrid").jsGrid({
        width: "100%",
        height: "400px",
    
        rowClass: function(item, itemIndex) {
            if(item.isDone) return "todo-Done";
        },

        editing: true,
        autoload: true,
        paging: true,
        pageLoading:true,

        pageIndex: 1,
        pageSize: 5,
        pageButtonCount: 10,
        
        controller: {
            loadData: function(parameters) {
                var d = $.Deferred();
                $.ajax({
                    url: "http://localhost:29801/todos"
                    ,data:parameters
                    ,dataType: "json"
                }).done(function(response) {
                    if(response.success) {
                        d.resolve({
                            data: response.data.items
                            ,itemsCount: response.data.totalCount
                        });
                    } else {
                        alert(response.message);
                        d.reject();
                    }
                });

                return d.promise();
            },
            insertItem: function(parameters) {
                var d = $.Deferred();
                $.ajax({
                    url: "http://localhost:29801/todos"
                    ,type: "POST"
                    ,contentType: "application/json; charset=utf-8"
                    ,data: JSON.stringify(parameters)
                    ,dataType: "json"
                }).done(function(response) {
                    if(response.success) {
                        alert('insert successFully');
                        d.resolve(response.data);
                    } else {
                        alert(response.message);
                        d.reject();
                    }
                });

                return d.promise();
            },
            updateItem: function(parameters) {
                var d = $.Deferred();
                $.ajax({
                    url: "http://localhost:29801/todos/"+parameters.todoId
                    ,type: "PUT"
                    ,contentType: "application/json; charset=utf-8"
                    ,data: JSON.stringify(parameters)
                    ,dataType: "json"
                }).done(function(response) {
                    if(response.success) {
                        alert('update successFully');
                        d.resolve(response.data);
                    }else {
                        alert(response.message);
                        d.resolve(previousItem);
                    }
                });

                return d.promise();
            },
            deleteItem: function(parameters) {
                var d = $.Deferred();
                $.ajax({
                    url: "http://localhost:29801/todos/"+parameters.todoId
                    ,type: "DELETE"
                    ,dataType: "json"
                }).done(function(response) {
                    if(response.success) {
                        alert('delete successFully');
                        d.resolve(response.data);
                    } else {
                        alert(response.message);
                        d.reject();
                    }
                });

                return d.promise();
            }
        },


        fields: [
            { name: "todoId", title: "ID", type: "number", width: 50, validate: "required", align: "center" },
            { name: "task", title: "Task", type: "text", width: 150 },
            { name: "referenceIds", title: "Reference Ids", type: "text", width: 100,
                itemTemplate: function(value) {
                    return jQuery.isEmptyObject(value) ? "" : value.map(id => '@'+id+'&nbsp;');
                }
            },
            { name: "createdDate", title: "Created Date", type: "text", width: 200, align: "center", readOnly:true,
                itemTemplate: function(value) {
                    return $("<div>").append(moment(value).format("YYYY-MM-DD HH:mm:ss"));
                }  
            },
            { name: "updatedDate", title: "Updated Date", type: "text", align: "center", width: 200, readOnly:true,
                itemTemplate: function(value) {
                    return $("<div>").append(moment(value).format("YYYY-MM-DD HH:mm:ss"));
                }  
             },
            {
                type: "control",
                deleteButton: false, 
                modeSwitchButton: false,
                editButton: false,
                headerTemplate: function() {
                    return $("<button>").attr("type", "button").addClass('jsgrid-button jsgrid-insert-button')
                            .on("click", function () {
                                showDetailsDialog("Add", {});
                            });
                },
                itemTemplate: function(value, item) {
                    return item.isDone ? "Done" : "";
                 }
            }
        ],
        onItemUpdating: function(args) {
            // previousItem = args.previousItem;
        },
        rowClick: function(args) {
            previousItem = $.extend({}, args.item);
            showDetailsDialog("Edit", args.item);
        },
        onPageChanged: function(args) {
            var searchCondition = {
                task: $("#search-task").val(),
                createdDate: $("#search-createdDate").val() ? moment($("#search-createdDate").val()).format('x') : null,
                updatedDate: $("#search-updatedDate").val() ? moment($("#search-updatedDate").val()).format('x') : null,
                pageSize: 5,
                pageIndex: (args.pageIndex -1)
            }
            $("#jsGrid").jsGrid("loadData", searchCondition).done(function() {
                console.log("data loaded");
            });
        },
        onDataLoaded: function(args) {

        }
    });

    $("#detailsDialog").dialog({
        autoOpen: false,
        width: 400,
        close: function() {
            $('#task-validationMsg').text("");
            $('#referIds-validationMsg').text("");
            $("#isDone").prop("checked",false);
            $("#detailsForm").validate().resetForm();
            $("#detailsForm").find(".error").removeClass("error");
        }
    });

    $("#detailsForm").validate({
        submitHandler: function() {
            formSubmitHandler();
        },
        resetForm: function() {
            $('#task-validationMsg').text("");
            $('#referIds-validationMsg').text("");
            $("#isDone").prop("checked",false);
        }
    });

    $("#detailsForm").validate({
        submitHandler: function() {
            formSubmitHandler();
        }
    });

    var formSubmitHandler = $.noop;

    var showDetailsDialog = function(dialogType, todo) {
        $("#todoId").val(todo.todoId);
        $("#task").val(todo.task);
        $("#referenceIds").val(todo.referenceIds);
        $("#createdDate").val(moment(todo.createdDate).format("YYYY-MM-DD HH:mm:ss"));
        $("#updatedDate").val(moment(todo.updatedDate).format("YYYY-MM-DD HH:mm:ss"));    
        $("#isDone").prop("checked", todo.isDone);
        if(todo.isDone){
            $("#isDone").attr('disabled',true);
        }else{
            $("#isDone").attr('disabled',false);
        }

        formSubmitHandler = function() {
            if(!$("#task").val()){
                $("#detailsForm").validate().resetForm();
                $('#task-validationMsg').text("required field");
            }else{
                $('#task-validationMsg').text("");
                if(/^(\d|,)*\.?\d*$/.test($("#referenceIds").val())){
                    var array = $("#referenceIds").val().split(",").map(Number);
                    var index = array.indexOf(parseInt($("#todoId").val()));
                    if(index === -1){
                        $('#referIds-validationMsg').text("");
                        saveTodo(todo, dialogType === "Add");
                    }else{
                        $("#detailsForm").validate().resetForm();
                        $('#referIds-validationMsg').text("reference id should be not include " + $("#todoId").val());
                    }
                }else{
                    $("#detailsForm").validate().resetForm();
                    $('#referIds-validationMsg').text("this format should be '{number},{number}' ");
                }
            }
        };

        $("#detailsDialog").dialog("option", "title", dialogType + " Todo").dialog("open");
    };

    var saveTodo = function(todo, isNew) {
        $.extend(todo, {
            todoId: parseInt($("#todoId").val(), 10),
            task: $("#task").val(),
            referenceIds: $("#referenceIds").val()? $("#referenceIds").val().split(",").map(Number) : null,
            createdDate: isNew ? new Date().getTime() : todo.createdDate,
            updatedDate: new Date().getTime(),
            isDone: $("#isDone").is(":checked")
        });

        $("#jsGrid").jsGrid(isNew ? "insertItem" : "updateItem", todo);
        $("#detailsDialog").dialog("close");
    };

    $("#testBtn").on("click", function(){
        var searchCondition = {
            task: $("#search-task").val(),
            createdDate: $("#search-createdDate").val() ? moment($("#search-createdDate").val()).format('x') : null,
            updatedDate: $("#search-updatedDate").val() ? moment($("#search-updatedDate").val()).format('x') : null,
            pageSize: 5,
            pageIndex: 1
        }
        $("#jsGrid").jsGrid("loadData", searchCondition).done(function() {
            console.log("data loaded");
        });
    });
});