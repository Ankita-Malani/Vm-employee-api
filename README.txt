# Getting Started

### Guides
The following guides illustrate how to use some features concretely:


1) git clone https://github.com/Ankita-Malani/Vm-employee-api.git
2) Run in terminal: mvn clean install
3) Start DemoApplication from run tab
4) open postman and import collection i have sent over email
5) Hit request following requests
  - file upload : Post - http://localhost:8080/api/employee/upload with file uploaded in form-data and no headers
  - deleted employee: Post - http://localhost:8080/api/employee/delete/22, 22 = employeeId
  - Upload employee:
            Post -  http://localhost:8080/api/employee/update,
            Header - Content-Type:application/json;charset=UTF-8
                     Accept:application/json;charset=UTF-8
            Body - inside raw radio -
                        {
                         "id" : 24,
                         "name": "Mehul Malani",
                         "age" : 26
                         }
  - getTaskStatus:
     Get - http://localhost:8080/api/task/getStatus/23, 23 = taskId returned as result of file upload


