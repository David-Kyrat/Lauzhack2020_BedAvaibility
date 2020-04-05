| hospital\_id | StName | StNb | NPA | Canton | Town | Capacity | FreeBeds |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| CHUV | Rue de Bugnon | 21 | 1011 | VD | Lausanne | 1554 | 233 |
| eHnv | Route de l'Hôpital | 3 | 1436 | VD | Chamblon | 330 | 0 |
| Fédération des Hôpitaux Vaudoix | Bois de Cery | 0 | 1008 | VD | Prilly | 0 | 0 |

| Patient\_id | FirstName | LastName | Age | RiskFactor | HealthState | fk\_hospital\_id | fk\_UnitType\_id |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| 0000 | Jean | Michel | 45 | 75 | Grave | CHUV | Intensive Care |

| UnitType\_id | Capacity | UsedBedNb | FreeBedNb | CovidBedNb | OtherBedNb | fk\_hospital\_id |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| Intensive Care | 0 | 0 | 0 | 0 | 0 | CHUV |
