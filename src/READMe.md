This simple project hosts a REST API to send XML with a GET Request and return the JSON conversion of the request's XML.

To run this program:

Open up the project folder in IntelliJ Community Edition

`Build the Project`

Open the application file found at the following filepath:
src/main/groovy/com/example/justransform/JustransformProjectApplication.groovy

`Run 'JustransformProjectApplication.groovy'`


Using Postman or another API request sending software:
Send a GET request to `localhost:8080/convert`

The body should be properly formatted xml.

====================================================================================
Examples of proper request bodies and their expected return:
Request Body:
```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<request>
  <username>rdara</username>
  <sendErrorsTo>rohithdara2@gmail.com</sendErrorsTo>
  <body>Send me the data from the cloud</body>
</request>
```

Corresponding Well Formatted Return JSON:
```json
{
    "name": "request",
    "children": [
        {
            "name": "username",
            "value": "rdara"
        },
        {
            "name": "sendErrorsTo",
            "value": "rohithdara2@gmail.com"
        },
        {
            "name": "body",
            "value": "Send me the data from the cloud"
        }
    ]
}
```


Request Body:
```xml
<messages>
  <note id="501">
    <to>Tove</to>
    <from>Jani</from>
    <heading>Reminder</heading>
    <body>Don't forget me this weekend!</body>
  </note>
  <note id="502">
    <to>Jani</to>
    <from>Tove</from>
    <heading>Re: Reminder</heading>
    <body>I will not</body>
  </note>
</messages>
```


Corresponding Well Formatted Return JSON:
```json
{
    "name": "messages",
    "children": [
        {
            "name": "note",
            "attributes": {
                "id": "501"
            },
            "children": [
                {
                    "name": "to",
                    "value": "Tove"
                },
                {
                    "name": "from",
                    "value": "Jani"
                },
                {
                    "name": "heading",
                    "value": "Reminder"
                },
                {
                    "name": "body",
                    "value": "Don't forget me this weekend!"
                }
            ]
        },
        {
            "name": "note",
            "attributes": {
                "id": "502"
            },
            "children": [
                {
                    "name": "to",
                    "value": "Jani"
                },
                {
                    "name": "from",
                    "value": "Tove"
                },
                {
                    "name": "heading",
                    "value": "Re: Reminder"
                },
                {
                    "name": "body",
                    "value": "I will not"
                }
            ]
        }
    ]
}
```

Examples of improper request bodies and their expected return:
Request Body (not XML format):
`Improper XML input`

Return Body:
Status: 400. The following is not a valid XML expression: Improper XML input

Request Body (no closing Tag on a child node):
```xml
<request>
  <username>rdara
  <sendErrorsTo>rohithdara2@gmail.com</sendErrorsTo>
  <body>Send me the data from the cloud</body>
</request>
```


Return:
```xml
Status: 400. The following is not a valid XML expression: 

<request>
  <username>rdara
  <sendErrorsTo>rohithdara2@gmail.com</sendErrorsTo>
  <body>Send me the data from the cloud</body>
</request>

```

