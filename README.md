Author: Alexander Castro

Implementaion of three CPU scheduling algorithms:

Description:

	Reads input from an input file that lists all of the processes.
	The input file should be formated with each process being on its
	own individual line. For each process, there are 5 fields:
		- PID
		- Priority
		- Arrival Time
		- Burst Time 
		- Total Ticks needed to finish

	The program will run a scheduling simulation using the following 
	CPU scheduling algorithms:
		- Non-Preemptive FCFS
		- Preemptive Priority
		- Round Robin
	
	The program will keep track of several statistical properties in 
	order to see how the algorithm performs on different process inputs. 
	The following are the statistics that will outputed after the simulation
	is run:
		- CPU utilization
		- Average Response Time
		- Average Turnaround Time 
		- Start and end time of each process

Compiling:

	The provided makefile should build the project for you. Run the following 
	command to use the makefile:

		make
	
	After you build the project, a bin folder should be created with all of the
	.class files. Additionally, a java JAR file should be placed in the current 
	directory.

Cleaning:

	If you wish to clean the project (Remove all binaries and executables), then 
	you can run the following command to do so:
		
		make clean

Running:

	The application has two modes:
		- Standard Mode
		- Debug Mode
	
	Standard Mode:
		This mode will run the simulation using the given input file and the 
		output the final statistics of the simulation.
	
		HOW TO RUN:
			Make sure the exe.jar file is in your current directory and then
			run the following command.

			java -jar exe.jar [Processes Input File]

	Debug Mode:
		This mode will run the simulation using the given input file and also
		log all steps made by the simulation. The final output will also include
		the final statistics that are produced in standard mode.
	
		HOW TO RUN:
			Make sure the exe.jar file is in your current directory and then 
			run the following command.

			java -jar exe.jar -d [Processes Input File]

Known Bugs:

	The are no known bugs for any of the scheduling algorithms

