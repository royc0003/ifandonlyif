import mysql.connector
import json
#219.74.174.70

def deEmojify(inputString):
    return inputString.encode('ascii', 'ignore').decode('ascii')

mydb = mysql.connector.connect(
  host='128.199.167.80',
  user='jozua',
  password='root',
  database="ifandonlyif"
)

mycursor = mydb.cursor()

with open('./data.json') as f:
    data = json.load(f)


for item in data:
    try:
        name = item['name']
    except KeyError:
        name = ""
    try:
        start = item['start']
    except KeyError:
        start = ""
    try:
        end = item['end']
    except KeyError:
        end = ""
    try:
        image = item['image']
    except KeyError:
        image = ""
    try:
        vendor = item['vendor']
    except KeyError:
        vendor = ""
    try:
        terms = item['terms']
        terms = deEmojify(terms)
    except KeyError:
        terms = ""
    try:
        category = item['category']
    except KeyError:
        category = ""

    '''
    print("1. Name: " + name)
    print("2. Start: " + start)
    print("3. End: " + end)
    print("4. Image: " + image)
    print(terms.encode())
    print("6. Category: " + category)
    print("===================================")'''

    add_new = "INSERT INTO deals (id, name, start, end, image, vendor, terms, category) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
    val = (None, name, start, end, image, vendor, terms, category)
    #IMPORTANT -- ADD TRIGGER UPON INSERT, IF ALREADY EXIST, DON'T ADD
    mycursor.execute(add_new, val)
    print("adding")

    mydb.commit()

# Need to figure this out
'''
delete_expire = "DELETE FROM deals WHERE end > currentDate"
mycursor.execute(delete_expire)
mydb.commit()'''



