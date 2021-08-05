# match-service

This is just a simplest match service is dedicated for searching matches using your interests (for now).

*Written in Java using Hibernate (for JPA) and PostgreSQL*

Service entities.

- User entity: stores information about the users. This is a private information for initialization on service and further authentication (hasn’t worked yet)

- MatchInfo entity: entity stores parameters user wants to find a match by.

- CommonAccount: is used to be shown as a result of match.

- Pictures entity: users are allowed to store photos that are attached to their CommonAccounts.
