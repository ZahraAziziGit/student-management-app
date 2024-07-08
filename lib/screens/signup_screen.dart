import 'dart:io';

import 'package:flutter/material.dart';

import 'package:radiohead/theme/theme.dart';
import 'package:radiohead/widgets/custom_scaffold.dart';
import 'package:radiohead/screens/login_screen.dart';
import 'package:radiohead/screens/welcome_screen.dart';

class SignUpScreen extends StatefulWidget {
  const SignUpScreen({super.key});

  @override
  State<SignUpScreen> createState() => _SignUpScreenState();
}

class _SignUpScreenState extends State<SignUpScreen> {
  final _formSignupKey = GlobalKey<FormState>();

  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _idController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  String? _username;
  String? _studentId;
  String? _password;
  bool _isPasswordVisible = false;
  bool idChecker = false;
  bool duplicateUsername = false;
  String response = '';

  @override
  void dispose() {
    _usernameController.dispose();
    _passwordController.dispose();
    _idController.dispose();
    super.dispose();
  }

  String? _validateUsername(String? value) {
    if (value == null || value.isEmpty) {
      return "Username must be filled";
    }
    if (value.length < 5) {
      return "Username must be more than 5 characters";
    }
    RegExp regex = RegExp(r'^[a-zA-Z0-9_]{5,}$');
    if (!regex.hasMatch(value)) {
      return "Only use English Alphabet, numbers and _";
    }
    return null;
  }

  String? _validateId(String? value) {
    if (value == null || value.isEmpty) {
      return "Student ID must be filled";
    }
    RegExp regex = RegExp(r'[0-9]{9}');
    if (!regex.hasMatch(value)) {
      return "Student ID includes 9 digits";
    }
    return null;
  }

  String? _validatePassword(String? value) {
    if (value == null || value.isEmpty) {
      return "Password must be filled";
    }
    if (_username != null && value.contains(_username!)) {
      return "Password must not contain the username";
    }
    if (value.length < 8) {
      return "password must be more than 8 characters";
    }
    RegExp regex =
        RegExp(r'^(?!.~).(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!?*#@]).+$');
    if (!regex.hasMatch(value)) {
      return "Password must contain:\n"
          "an uppercase letter and a lowercase letter\n"
          "a number and a special character (!?*#@)\n"
          "and no '~'";
    }
    return null;
  }

  @override
  Widget build(BuildContext context) {
    return CustomScaffold(
      leading: IconButton(
        icon: const Icon(Icons.arrow_back),
        onPressed: () {
          Navigator.pushAndRemoveUntil(
            context,
            MaterialPageRoute(builder: (context) => const WelcomeScreen()),
            (Route<dynamic> route) => false,
          );
        },
      ),
      child: Column(
        children: [
          const Expanded(
            flex: 1,
            child: SizedBox(
              height: 6,
            ),
          ),
          Expanded(
            flex: 20,
            child: Container(
              margin: const EdgeInsets.all(20.0),
              padding: const EdgeInsets.fromLTRB(30.0, 50.0, 30.0, 10.0),
              decoration: const BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.all(
                  Radius.circular(40.0),
                ),
              ),
              child: SingleChildScrollView(
                child: Form(
                  key: _formSignupKey,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Text(
                        "Sign up!",
                        style: TextStyle(
                          fontSize: 35.0,
                          fontWeight: FontWeight.bold,
                          fontFamily: 'Roboto',
                          color: lightColorScheme.secondary,
                        ),
                      ),
                      const SizedBox(
                        height: 30.0,
                      ),
                      TextFormField(
                        controller: _usernameController,
                        decoration: InputDecoration(
                          label: const Text("Username"),
                          labelStyle: TextStyle(
                            color: lightColorScheme.secondary,
                            fontSize: 16.0,
                            fontFamily: 'Courier New',
                          ),
                          hintText: "Enter your username",
                          hintStyle: const TextStyle(
                            color: Colors.black26,
                            fontFamily: 'Times New Roman',
                          ),
                          border: OutlineInputBorder(
                            borderSide: const BorderSide(
                              color: Colors.black12,
                            ),
                            borderRadius: BorderRadius.circular(10),
                          ),
                          enabledBorder: OutlineInputBorder(
                            borderSide: const BorderSide(
                              color: Colors.black12,
                            ),
                            borderRadius: BorderRadius.circular(10),
                          ),
                        ),
                        validator: _validateUsername,
                        onChanged: (value) {
                          setState(() {
                            _username = value;
                          });
                        },
                      ),
                      const SizedBox(
                        height: 25.0,
                      ),
                      TextFormField(
                        keyboardType: TextInputType.number,
                        controller: _idController,
                        decoration: InputDecoration(
                          label: const Text("Student ID"),
                          labelStyle: TextStyle(
                            color: lightColorScheme.secondary,
                            fontSize: 16.0,
                            fontFamily: 'Courier New',
                          ),
                          hintText: "Enter your student ID",
                          hintStyle: const TextStyle(
                            color: Colors.black26,
                            fontFamily: 'Times New Roman',
                          ),
                          border: OutlineInputBorder(
                            borderSide: const BorderSide(
                              color: Colors.black12,
                            ),
                            borderRadius: BorderRadius.circular(10),
                          ),
                          enabledBorder: OutlineInputBorder(
                            borderSide: const BorderSide(
                              color: Colors.black12,
                            ),
                            borderRadius: BorderRadius.circular(10),
                          ),
                        ),
                        validator: _validateId,
                        onChanged: (value) {
                          setState(() {
                            _studentId = value;
                          });
                        },
                      ),
                      const SizedBox(
                        height: 25.0,
                      ),
                      // password
                      TextFormField(
                        controller: _passwordController,
                        obscureText: !_isPasswordVisible,
                        obscuringCharacter: '*',
                        decoration: InputDecoration(
                          label: const Text("Password"),
                          labelStyle: TextStyle(
                            color: lightColorScheme.secondary,
                            fontSize: 16.0,
                            fontFamily: 'Courier New',
                          ),
                          hintText: "Enter a safe password",
                          hintStyle: const TextStyle(
                            color: Colors.black26,
                            fontFamily: 'Times New Roman',
                          ),
                          border: OutlineInputBorder(
                            borderSide: const BorderSide(
                              color: Colors.black12,
                            ),
                            borderRadius: BorderRadius.circular(10),
                          ),
                          enabledBorder: OutlineInputBorder(
                            borderSide: const BorderSide(
                              color: Colors.black12,
                            ),
                            borderRadius: BorderRadius.circular(10),
                          ),
                          suffixIcon: IconButton(
                            icon: Icon(
                              _isPasswordVisible
                                  ? Icons.visibility
                                  : Icons.visibility_off,
                            ),
                            onPressed: () {
                              setState(() {
                                _isPasswordVisible = !_isPasswordVisible;
                              });
                            },
                          ),
                        ),
                        validator: _validatePassword,
                        onChanged: (value) {
                          setState(() {
                            _password = value;
                          });
                        },
                      ),
                      const SizedBox(
                        height: 50.0,
                      ),
                      SizedBox(
                        width: double.infinity,
                        child: ElevatedButton(
                          onPressed: () async {
                            await signUp();
                            if (_formSignupKey.currentState!.validate() &&
                                idChecker &&
                                !duplicateUsername) {
                              ScaffoldMessenger.of(context).showSnackBar(
                                const SnackBar(
                                  content: Text("Success!"),
                                ),
                              );
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (e) => const LogInScreen(),
                                ),
                              );
                            } else if (!idChecker && !duplicateUsername) {
                              ScaffoldMessenger.of(context).showSnackBar(
                                const SnackBar(
                                  content: Text("Student ID was not found"),
                                ),
                              );
                            } else if (!idChecker && duplicateUsername) {
                              ScaffoldMessenger.of(context).showSnackBar(
                                const SnackBar(
                                  content:
                                      Text("Username has been already taken"),
                                ),
                              );
                            }
                          },
                          style: ElevatedButton.styleFrom(
                            backgroundColor: lightColorScheme.primary,
                            foregroundColor: Colors.white,
                          ),
                          child: const Text("SIGN UP"),
                        ),
                      ),
                      const SizedBox(
                        height: 30.0,
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          const Text(
                            "Already have an account? ",
                            style: TextStyle(
                              color: Colors.black45,
                            ),
                          ),
                          GestureDetector(
                            child: Text(
                              "Log in!",
                              style: TextStyle(
                                fontWeight: FontWeight.bold,
                                color: lightColorScheme.primary,
                              ),
                            ),
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (e) => const LogInScreen(),
                                ),
                              );
                            },
                          ),
                        ],
                      ),
                      const SizedBox(
                        height: 20.0,
                      ),
                    ],
                  ),
                ),
              ),
            ),
          ),
          const Expanded(
            flex: 1,
            child: SizedBox(
              height: 6,
            ),
          ),
        ],
      ),
    );
  }

  Future<String> signUp() async {
    try {
      final serverSocket = await Socket.connect("192.168.1.102", 2041);
      serverSocket.write('signup~$_username~$_studentId~$_password\u0000');
      serverSocket.flush();

      serverSocket.listen((socketResponse) {
        setState(() {
          response = String.fromCharCodes(socketResponse);
        });
      });

      await Future.delayed(const Duration(seconds: 1));
      serverSocket.close();

      print("-----------server response is: { $response }");

      if (response == "found") {
        idChecker = true;
        duplicateUsername = false;
      } else if (response == "duplicate") {
        duplicateUsername = true;
        idChecker = false;
      } else {
        idChecker = false;
        duplicateUsername = false;
      }
    } catch (e) {
      print("Error: $e");
      if (e is SocketException) {
        print(
            'SocketException: ${e.message}, address: ${e.address}, port: ${e.port}');
      }
    }
    return response;
  }
}
