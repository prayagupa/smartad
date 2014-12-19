smartad-front
===========

**AngularJS - Scala/Play - Guice - PlayReactiveMongo**

Getting Started
----------

```
/usr/local/mongodb-linux-x86_64-2.6.0/bin/mongod

/usr/local/play-2.2.2/play "~run 8443"
```

Note: This will create a MongoDB Collection for you automatically, a free-be from the Driver! 


how it works
------------------

```

public/partials/view.html                                      | 
|                                                              |
|                                                              |
|                                                              |
|____app/assets/javascripts/users/UserCtrl.coffee              |
     |                                                         |
     |                                                         |
     |                                                         |
     |app/assets/javascripts/users/UserService.coffee ---------|-------->  app/controllers/Users.scala

```
