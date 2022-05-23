# Math Interpreter



## Todo

* [x] lexer scanning for tokens
  * [x] dealing with single char tokens
    * [X] Need to test this
  * [x] dealing with numbers
    * [x] Test cases for it
  * [x] Add test cases for an entire expression
  * [x] Add ignore white space in lexer
* [ ] error handling for lexer
  * [ ] don't execute if error in lexing
  * [x] error logging to user
  * [x] print error with arrow showing where it occurred in expression
* [x] peek function for looking ahead with no increment
* [x] Testing script for lexer
* [ ] parser
  * [x] define grammar
  * [ ] make all the node types
    * [ ] implement display of expression for easy debugging / testing
  * [ ] make recursive descent parser
  * [ ] test recursive descent parser



## Grammar

```
expression -> factor (("+"|"-") factor)*
factor -> power (("*"|"/") power)*
power -> primary ("^" primary)*
unary -> "-" primary
primary -> NUMBER
```

