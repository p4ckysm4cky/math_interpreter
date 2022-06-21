# Math Interpreter



## Todo

 * [ ] Use a try except block in the parser to stop crashing the entire program

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
  * [x] make all the node types
    * [x] implement display of expression for easy debugging / testing
  * [x] make recursive descent parser
    * [x] Make helper functions for parsing
    * [x] Make recursive descent parser prototype
    * [x] test recursive descent parser
    * [ ] Rework unary grammar, so it allows `--34.97`
    * [ ] Rework addition grammar, so it allows `++34.97`
    * [ ] Add error handling for incorrect input tokens
  * [ ] Evaluate expression
    * [x] Implement Visitor pattern
    * [x] Run postorder traversal on tree
    * [ ] Add error handling to traversal (AstVisitor)
    * [ ] test postorder traversal
    * [ ] Evaluate expression from postorder traversal



## Grammar

```
expression -> factor (("+"|"-") factor)*
factor -> power (("*"|"/") power)*
power -> unary ("^" unary)*
unary -> "-" primary | primary
primary -> NUMBER | "(" expression ")"
```

