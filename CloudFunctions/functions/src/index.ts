import * as functions from 'firebase-functions';

// // Start writing Firebase Functions
// // https://firebase.google.com/docs/functions/typescript


// Get all deals
export const getAllDeals = functions.https.onRequest((request, response) => {
    response.send("Should query database and return a list of all deals");
});

// Get match
export const getMatch = functions.https.onRequest((request, response) => {
    response.send("Query database for potential match then run through algorithm for a match, return match");
});

// 