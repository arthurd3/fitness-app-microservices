db = db.getSiblingDB('fitnessrecommendation');

db.createCollection('recommendations');

print("Database 'fitnessrecommendation' and collections 'recommendations' is created with success.");