import tkinter as tk
from tkinter import messagebox
import conn
import parcel_calculator
import mysql.connector

class ParcelCalculatorGUI:
    def __init__(self, master):
        self.master = master
        master.title("Parcel Calculator")
        master.configure(bg="#f0f0f0")  # Set background color

        # Create length input label and entry field
        self.length_label = tk.Label(master, text="Length (cm):", bg="#f0f0f0", fg="#333333")
        self.length_label.grid(row=0, column=0, padx=10, pady=5, sticky='e')
        self.length_entry = tk.Entry(master)
        self.length_entry.grid(row=0, column=1, padx=10, pady=5)

        # Create width input label and entry field
        self.width_label = tk.Label(master, text="Width (cm):", bg="#f0f0f0", fg="#333333")
        self.width_label.grid(row=1, column=0, padx=10, pady=5, sticky='e')
        self.width_entry = tk.Entry(master)
        self.width_entry.grid(row=1, column=1, padx=10, pady=5)

        # Create height input label and entry field
        self.height_label = tk.Label(master, text="Height (cm):", bg="#f0f0f0", fg="#333333")
        self.height_label.grid(row=2, column=0, padx=10, pady=5, sticky='e')
        self.height_entry = tk.Entry(master)
        self.height_entry.grid(row=2, column=1, padx=10, pady=5)

        # Create weight input label and entry field
        self.weight_label = tk.Label(master, text="Weight (kg):", bg="#f0f0f0", fg="#333333")
        self.weight_label.grid(row=3, column=0, padx=10, pady=5, sticky='e')
        self.weight_entry = tk.Entry(master)
        self.weight_entry.grid(row=3, column=1, padx=10, pady=5)

        # Create calculate button
        self.calculate_button = tk.Button(master, text="Calculate", command=self.calculate_price, bg="#4CAF50", fg="white")
        self.calculate_button.grid(row=4, column=0, padx=10, pady=10, sticky='e')

        # Create reset button
        self.reset_button = tk.Button(master, text="Reset", command=self.reset_fields, bg="#f44336", fg="white")
        self.reset_button.grid(row=4, column=1, padx=10, pady=10, sticky='w')

        # Create save button
        self.save_button = tk.Button(master, text="Save to Database", command=self.save_to_database, bg="#2196F3", fg="white")
        self.save_button.grid(row=5, column=0, columnspan=2, padx=10, pady=10)

        # Create price label
        self.price_label = tk.Label(master, text="", bg="#f0f0f0", fg="#333333")
        self.price_label.grid(row=6, column=0, columnspan=2, pady=10)

    def calculate_price(self):
        try:
            # Get parcel dimensions and weight from entry fields
            length = float(self.length_entry.get())
            width = float(self.width_entry.get())
            height = float(self.height_entry.get())
            weight = float(self.weight_entry.get())

            # Validate the inputs
            if length <= 0 or width <= 0 or height <= 0 or weight <= 0:
                raise ValueError

            # Calculate parcel price using imported function
            price = parcel_calculator.calculate_price(length, width, height, weight)

            # Update price label with calculated price
            self.price_label.config(text="The price of your parcel is: $" + str(price))
            
            # Show success message
            messagebox.showinfo("Calculation Complete", "The price calculation was successful!")
        
        except ValueError:
            # Show error message
            messagebox.showerror("Invalid Input", "Please enter valid numerical values for all fields.")

    def reset_fields(self):
        # Clear all entry fields and reset the price label
        self.length_entry.delete(0, tk.END)
        self.width_entry.delete(0, tk.END)
        self.height_entry.delete(0, tk.END)
        self.weight_entry.delete(0, tk.END)
        self.price_label.config(text="")

    def save_to_database(self):
        try:
            # Get parcel dimensions and weight from entry fields
            length = self.length_entry.get()
            width = self.width_entry.get()
            height = self.height_entry.get()
            weight = self.weight_entry.get()
            price = self.price_label.cget("text").split("$")[-1].strip()

            # Validate the inputs
            if not length or not width or not height or not weight or not price:
                raise ValueError("All fields must be filled out.")

            # Establish connection to the database
            connection = mysql.connector.connect(
                host="localhost",
                user="root",
                password="",
                database="Lelemove_System"
            )
            cursor = connection.cursor()

            # Insert the data into the inventory table
            cursor.execute('''INSERT INTO inventory (Item_id, item_height, Item_width, Item_length, Item_volume, Item_price) 
                              VALUES (%s, %s, %s, %s, %s, %s)''', 
                           (None, height, width, length, str(float(length) * float(width) * float(height)), price))
            connection.commit()
            cursor.close()
            connection.close()

            # Show success message
            messagebox.showinfo("Success", "The data has been successfully stored in the database.")
        
        except ValueError as ve:
            # Show error message
            messagebox.showerror("Invalid Input", str(ve))
        except mysql.connector.Error as e:
            # Show error message
            messagebox.showerror("Database Error", f"Error storing data in the database: {e}")

if __name__ == "__main__":
    # Ensure the database and table are created
    conn.database_table()

    root = tk.Tk()
    parcel_calculator_gui = ParcelCalculatorGUI(root)
    root.mainloop()
