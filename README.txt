1. How to start the application: 
	In command prompt open the dist folder and write the following command: java -jar "AtomCounter.jar"

2. How to use the application:
	After the application starts you will be prompted to input a chemical molecule.
	After pressing Enter, the output will be displayed in the console.

3. How does the application work:
	The app uses the following classes:
	Class "Atom" is used to store information for the individual atom. Each atom has a name which is stored in the "elementName" property and a count (number of occurrences in the molecule). The class has a single constructor which sets the first letter of the element name and defaults the count to 1. There are also getter and setter for each property.
	Class "AtomCounter" is the entry point of the application with its "main" function. There are a few static properties which are used to store information for the counted atoms.
	1. finalList stores the final processed atoms.
	2. stack is used to process the atoms in the groups (between brackets).

	Functions "countAtoms" and "processStack" are responsible for counting the atoms. The logic is as follows:
	1. Iterate over the chars of the string and process each one:
	a. If the char is an upper case letter, create a new Atom. If the stack is empty, add it to the finalList, if not add it to the stack itself.
	b. If the char is a lower case letter, add it to the last created atom's element name (stored in lastAtom variable).
	c. If the char is a digit, check how many digits are there (in case the number after the atom is larger than 9). Parse the digit(s) to an integer and set it to the count property of the atom object (latestAtom). This should be the end of the current atom, so free the latestAtom variable.
	d. If the char is an opening bracket: (, {, [ -> add it to the stack.
	e. If the char is anything else (presuming it should be a closing bracket) this should mark the end of the group of atoms. Get the multiplier for the group from the next char (either a number or the beginning of a new atom or the end of the molecule.In the last two cases use the number 1). Process the stack with the multiplier (description bellow).
	2. Group the atoms in a TreeMap (element name is key, count is value) by their element (name) in order to count them (one atom may have occurred in more than one place in the molecule, the TreeMap automatically sorts them alphabetically)
	3. Output the result in the console.
	
	Processing the stack:
	1. Create a temp list (for atoms);
	2. Pop item from the stack:
	a. If the item is an Atom, multiply its count with the group multiplier and add it to the temp list.
	b. If the item is not an Atom (it should be an opening bracket) and the stack is empty, this means that the whole group is processed. In that case, move the atoms from the temp list to the final list.
	c. If the item is not an Atom and the stack is not empty, this means this was a sub group in a group. In that case return all the atoms from the temp list back into the stack.

	
		
	