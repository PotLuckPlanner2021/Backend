# Back-end



## USERS ENDPOINTS 👤:

 **/[GET] endpoint for all users in the database /**
```
/api/users
```

**/[POST] - Register endpoint for a new user in the database/**
```
/api/users/user
```
**REQUIRED KEY/VALUES
```
{

 *username: STRING
 *password: STRING
 *primaryemail: STRING
 *roles: [
   {
        role: {
        roleid: 1  // 1 is for ORGANIZER, 2 is for GUEST
        }
   }, 
   {
        role: {
          roleid: 2  // 1 is for ORGANIZER, 2 is for GUEST
        }
   }
 ]
** NOTE; A USER CAN HAVE MULTIPLE ROLES:  ** 
}
```

**/[POST] - Login endpoint for a new user in the database/**
```
/login
```
**REQUIRED KEY/VALUES
```
{

 *username: STRING
 *password: STRING
 
 }
 ```

**IF THE USER IS AUTHENTICATED THEN THIS WILL RETURN AN ACCESS TOKEN, MAKE FURTHER REQUESTS WITH THIS TOKEN**

---------------------------------------------------------------------------
## POTLUCKS ENDPOINTS 🥯🍞🥯🥕:

 **/[GET] endpoint for all potlucks  in general in the database /**

```
/potlucks/potlucks
```
------------------------------------------------------
**COMING SOON**
**[GET] endpoint for getting a single potluck based on the potluck_id in the database **   
**COMING SOON**

```
/potlucks/:potluck_id   
```
--------------------------------------------------------



 **[POST] endpoint for creating a potluck for a user /**
 
```
/potlucks/:user_id/potlucks/
```

**REQUIRED KEY/VALUES
```
{

  *name: STRING
  *date: STRING,
  *time: STRING,
  *location: STRING,
  *host: STRING,
  *theme: STRING
  *guests: [
        {
            "guestname": STRING     //REQUIRED KEY/VALUE
        },
         {
            "guestname": STRING     //REQUIRED KEY/VALUE
        }
        etc...
    ]
  *items: [
        {
            itemname: STRING       //REQUIRED KEY/VALUE
        },
        
        {
            itemname: STRING        //REQUIRED KEY/VALUE
        }
        
        etc...
        
    ]
  
}
```

**REQUIREMENTS; AN ORGANIZER MUST BE LOGGED IN AND HAVE AN ACCESS TOKEN TO MAKE THE POST REQUEST TO CREATE A POTLUCK  **



 **/[PUT] endpoint for updating a user's potluck /**

```
/potlucks/potluck/:potluckid
```
**REQUIRED KEY/VALUES   // SAME AS POSTING A POTLUCK 👆
```
{
......
}
```


**NOTE; DO A GET REQUEST WITH THE POTLUCK ID A USER WANTS TO UPDATE IN ORDER TO AUTOMATICALLY FILL OUT THE INPUT FIELDS WITH EXISTING POTLUCK VALUES**


 **/[DELETE] endpoint for a potluck in the database /**

```
/potlucks/potluck/:potluckid
```
