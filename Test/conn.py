import mysql.connector 

def database_table():
    try:
        connection = mysql.connector.connect(
            host="localhost",
            user="root",
            password=""
        )
        cursor = connection.cursor()

        # Create the database if it doesn't exist
        cursor.execute("CREATE DATABASE IF NOT EXISTS Lelemove_System")

        # Switch to the database
        cursor.execute("USE Lelemove_System")

        # Create the user table if it doesn't exist
        cursor.execute('''CREATE TABLE IF NOT EXISTS inventory (
                            Item_id VARCHAR(10) AUTO_INCREMENT PRIMARY KEY,
                            item_height VARCHAR(5),
                            Item_width VARCHAR(5),
                            Item_length VARCHAR(5),
                            Item_volume VARCHAR(10),
                            Item_price VARCHAR(5)
                        )''')

        connection.commit()
        cursor.close()
        connection.close()
        print("Database and tables created successfully.")
    except mysql.connector.Error as e:
        print("Error creating database and tables:", e)