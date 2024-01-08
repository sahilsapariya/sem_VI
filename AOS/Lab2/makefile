.PHONY : circle square

circle: ccompile crun
	@echo "execution completed for circle"

ccompile:
	@gcc circle.c -o circle
crun:
	@./circle


square: scompile srun
	@echo "execution completed for square"
	
scompile:
	@gcc square.c -o square

srun:
	@./square

clean:
	@rm -f circle.c circle square square.c

all:
	@gcc circle.c -o circle
	@gcc square.c -o square
	@./circle
	@./square

.DEFAULT_GOAL := all