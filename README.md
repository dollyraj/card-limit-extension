## Extending Offers on Limits of Credit Card to Account Holders

###    This project mainly deals with the Backend of Credit card System for extending offers on limits of Credit Card to the Account Holders.

       There is a home controller which will receive all the user HTTP requests.
       Once the Controller receives the request, it sends to either Account Service for Account related requests or it will send to OfferService for Limit Offers related requests.
       Features it provides:
        Account creation
        Account Fetching
        Creating Offers for Limits( Account Limit , Per Transaction Limit ) on credit card.
        Users can accept or reject the offers.
        If user accepts the offer, their current limit will become their last limit and the new limit from the offer will become their current limit. (Mainitaining current and last limit value for both the limits) and also update the limit_modified_time.
        Also, Handling possible edge case scenarios and exceptions.
        Tech Stack used : Java , Spring Boot , MySQL
