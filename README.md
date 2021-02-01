# TrueAccordCodingChallenge

#Approach
1. Created class whose attributes are mapped with the json output from the consumed api. Mapped Debt table with Debt class,
   Payment Plans table with PaymentPlans class and Payments table with Payments class.
2. To display the required output, created a class DebtDetails which will have the feilds corresponding to the given requirement.
3. I have made use of ArrayList to store the data consumed from the apis. debtList will store the data from debt api, 
   paymentPlanList will store data from paymentplan api and paymentsList will store data from payments api.
4. I have also made use if HashMap to store PaymentPlan corresponding to debtId for making the calculation easy.
5. For testing the calculated result I have wrote 5 test cases corresponding to 5 debt ids.

#How to run the project
1. Download or git clone the project.
2. In the command promt go to the location of the project. 
3. To run the project use the command :  mvn exec:java -Dexec.mainClass=com.trueaccordcodingchallenge.App
4. To run the test cases use the command :  mvn test
5. If using eclipse ide, in the App class right click and run as Java Application
6. To run the test cases, in AppTest class right click and run as JUnit Test

