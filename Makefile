COMPIL_SERVER = javac
FLAG_SERVER =
NAME_SERVER = Server

all:$(NAME_SERVER)

$(NAME_SERVER): $(NAME_SERVER).java
	$(COMPIL_SERVER) $(FLAG_SERVER) $(NAME_SERVER).java

clean:
	rm $(NAME_SERVER).class