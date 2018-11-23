##  Ressource de l'api

# Lister tout les resource 
GET :
Uri : http://localhost:8082/imx/rest/v1/individus
Response : 
    
                       [
                           {
                              "cle": "0103567975",
                              "type": "ENTREPRISE",
                              "nom": "BPIFRANCE",
                              "parent": "0",
                              "status": "valid",
                              "siege": "true"
                           },
                           {
                              "cle": "010356725000012",
                              "type": "ETABLISSEMENT",
                              "nom": "CONSTRUCTION",
                              "parent": "0103567250",
                              "status": "valid",
                              "siege": "true"
                           },
                           {
                              "cle": "5898974",
                              "type": "INTERLOCUTEUR",
                              "nom": "AHMED",
                              "parent": "010356725000013",
                              "status": "valid",
                              "siege": "false"
                            },
                            {
                              "cle": "101672",
                              "type": "ADRESSE",
                              "nom": "Paris",
                              "parent": "010356725000013",
                              "status": "valid",
                              "siege": "false"
                             }
                          ]
                          
# Lister par type de resources
GET :
Uri : http://localhost:8082/imx/rest/v1/individus/{type}

   le type peut être soit : 
   SIG pour dire que la ressource est une 'Entreprise' ,  
   ETA pour dire que la ressource est une  'Etablissement' , 
   ADR pour dire que la ressource est une  'Adresse' , 
   INT pour dire que la ressource est une  'Interlocuteur'
   
   Exemple :  Lister tous les entreprises : 
   
       URI:http://localhost:8082/imx/rest/v1/individus/SIG
       Response : 

                      [
                         {
                          "cle": "0103567975",
                          "type": "ENTREPRISE",
                          "nom": "BPIFRANCE",
                          "parent": "0",
                          "status": "valid",
                          "siege": "true"
                         }
                      ]  
# Lister par type et cle 
GET :
Uri : http://localhost:8082/imx/rest/v1/individus/{type}/{cle}

   le type peut être soit : SIG 'Entreprise' , ETA 'Etablissement' , ADR  'Adresse' , INT 'Interlocuteur'
   La clé c'est l'identifient unique de chaque ressources.
   
   Exemple :  Lister une Etablissement : 
   
       URI:http://localhost:8082/imx/rest/v1/individus/SIG/010356725000012
       Response : 

                         [
                           {
                              "cle": "010356725000012",
                              "type": "ETABLISSEMENT",
                              "nom": "CONSTRUCTION",
                              "parent": "0103567250",
                              "status": "valid",
                              "siege": "true"
                           }
                         ]      
                         
     si la clé n'existe pas dans la base de donnée 
     Reponse sera : 
     
           {
             "status": "IMX-ERRORS",
             "message": "fonctionelle",
             "listErrors": [
                                {
                                    "code": "IMX-0007",
                                    "title": "could not fount the parameter : 41254",
                                    "description": "Parameter introuvable"
                                }
                            ]
            }                        
                         
# Ajouter une ressource dans la base de donnée 
POST

   Uri : http://localhost:8082/imx/rest/v1/individus

   Request body : 
                         {
                          "cle": "xxxxxx",
                          "type": "xxxxxxx",
                          "nom": "xxxxxxxx",
                          "parent": "xxxxxx",
                          "status": "xxxxxxxx",
                          "siege": "xxxxxx"
                         }

    Response : 
    1) si la requet est bien definie :
       
            RESOURCE  Ok.
    2) si la requet body est vide : 
    
            {
             "status": "IMX-ERRORS",
             "message": "Fonctionelle",
             "listErrors": [
                              {
                               "code": "IMX-0005",
                               "title": "Empty Body",
                               "description": "Empty Body"
                               }
                            ]
            }  
    3) si l'un des paramètres est mal définit :
    
            {
              "status": "IMX-ERRORS",
              "message": "ERREUR PARAMETER",
              "listErrors": [
                              {
                               "code": "IMX-0001",
                               "title": "Type de Parameter",
                               "description": "RESOURCE TYPE"
                              }
                             ]
             } 
             
     4) si la resource existe deja :
     
              {
               "status": "IMX-ERRORS",
               "message": "Fonctionelle ",
               "listErrors": [
                                 {
                                   "code": "IMX-0004",
                                   "title": "RESOURCE Exist in database",
                                   "description": "Data Duplicate"
                                 }
                              ]
              }
      5) si la resource n'a pas de liaison : 
            Exemple : si en veux creér une Etablissement et on a définit un parent qui n'est pas un clé pour tel entreprise dans la base :         
   
             Response : 
             
                         {
                           "status": "IMX-ERRORS",
                           "message": "Fonctionelle",
                           "listErrors": [
                                          {
                                            "code": "IMX-0002",
                                            "title": "Parent: 0207102 not Fount to add   ETABLISSEMENT",
                                            "description": "dependence"
                                          }
                                         ]
                          }
                          
                                
                          
                          
# Modifier une ressource dans la base de donnée 
PUT 

   Uri : http://localhost:8082/imx/rest/v1/individus/{clé}

   Request body : 
                         {
                          "cle": "xxxxxx",
                          "type": "xxxxxxx",
                          "nom": "xxxxxxxx",
                          "parent": "xxxxxx",
                          "status": "xxxxxxxx",
                          "siege": "xxxxxx"
                         }

    Réponse : 
    1) si la requet est bien definie :
       
            RESOURCE  Modified.
    2) si la requet body est vide : 
    
            {
             "status": "IMX-ERRORS",
             "message": "Fonctionelle",
             "listErrors": [
                              {
                               "code": "IMX-0005",
                               "title": "Empty Body",
                               "description": "Empty Body"
                               }
                            ]
            }   
            
            3) si la clé est introuvable : 
            Exemple : si en veux creér une Etablissement et on a définit un parent qui n'est pas un clé pour tel entreprise dans la base :         
   
             Response :                          
                  {
                    "status": "IMX-ERRORS",
                    "message": "fonctionelle",
                    "listErrors": [
                                      {
                                        "code": "IMX-0007",
                                        "title": "could not fount the parameter : 41254",
                                        "description": "Parameter introuvable"
                                      }
                                   ]
                  }                      