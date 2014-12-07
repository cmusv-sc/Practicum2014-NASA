NASA Practicum - Trust Based Publication Network
==========


This project has been developed for a practicum project study by Carnegie Mellon University Silicon Valley under the guidance of Professor Jia Zhang.


Goal
------

We aim to provide an easily understandable and interpretable visualization to find out relevant and reliable information about publications.


Technologies We Use
------

- Play Framework
- Mysql
- D3 JS


How to Load Data
-------

DBLP and Citations data sources are required for the application. To load these, you can find the details in Technical Document under docs folder.


How to Run the Application
-------


You need to have play or activator installed on your machine. After that, you browse to the backend and frontend server separately and run the command to start them individually.

<code>play "run [port number]"</code>
or
<code>activator "run [port number]"</code>

If you need to import the projects into your IDE, then you need to run another command to fix the dependencies the project requires. After you browse to the backend and frontend folders, run the following command:

<code>play eclipsify</code>
or
<code>activator eclipse</code>