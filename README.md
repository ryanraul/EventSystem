

# AC2 - Programção Orientada a Objetos II
### **Desenvlvido por: Raul Ryan Deaque Silva**
***JSON's utilizados nos metodos POST e UPDATE***


## Admins
* **Post**

		{
          "name": "admin_test",
          "email": "admin_test@email.com",
          "phoneNumber": "15-1234-12345"
		}
* **Update**

		{
          "email": "admin_up@email.com",
          "phoneNumber": "15-9999-99999"
		}
## Attendees
* **Post**

		{
          "name":  "Joao",
          "email":  "j@email.com",
          "balance":  400
		}
* **Update**

		{
          "email":  "rr@email.com",
          "balance":  250
		}
## Places
* **Post**

		{
          "name":  "Itapetininga",
          "address":  "Rua Itapetininga"
		}
* **Update**

		{
          "name":  "Sao Paulo",
          "address":  "Rua Sao Paulo"
		}
## Events
* **Post**

		{
          "name":  "Spring boot",
          "description":  "Spring boot learners",
          "startDate":  "2021-06-15",
          "endDate":  "2021-06-20",
          "startTime":  "19:00:00",
          "endTime":  "22:00:00",
          "emailContact":  "sb@email.com",
          "amountFreeTickets":  100,
          "amountPayedTickets":  300,
          "priceTicket":  35,
          "adminId":  1
		}
* **Update**

		{
          "description":  "React learners",
          "startDate":  "2021-06-15",
          "endDate":  "2021-06-20",
          "startTime":  "19:00:00",
          "endTime":  "23:00:00",
          "emailContact":  "React@email.com",
          "amountFreeTickets":  50,
          "amountPayedTickets":  400,
          "priceTicket":  10
		}
