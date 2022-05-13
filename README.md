# Getting Started

### Steps to run Application

Import Application to any IDE as maven project.

Start Application it will run in default 8080 port.

Browse H2 DB console (Don't change anything it will take default properties)
*http://localhost:8080/company/h2-console/login.do?jsessionid=edc5db761466a8c07d01d33fe299b4c5
*Import sample data by running below DML Script .

### Scripts
insert into EMPLOYEE values ('1','E10001','Alex');
insert into EMPLOYEE values ('2','E10002','Austin');
insert into DEPARTMENT values ('1','D10001','Consent');
insert into PROJECT values ('1','D10001','041a6aa0-070e-449c-b527-fb48f92c8615','CM','P1001');
insert into PROJECT values ('2','D10001','041a6aa0-070e-449c-b527-fb48f92c8615','CM','P1002');
insert into EMP_PROJECT values ('1','1');
insert into EMP_PROJECT values ('1','2');
insert into DEPARMENT_PROJECT values ('1','1');
insert into EMP_DEPARTMENT values ('1','1');

### Sample request CURL to access API

# Get all employees associate with Project
curl --location --request GET 'http://localhost:8080/company/project/employee/041a6aa0-070e-449c-b527-fb48f92c8615'

# Remove an employee from Project

curl --location --request DELETE 'http://localhost:8080/company/project/disassociate' \
--header 'Content-Type: application/json' \
--data-raw '{
"projectGuid":"041a6aa0-070e-449c-b527-fb48f92c8615",
"departmentCode":"D10001",
"employeeCode":"E10001"
}'
