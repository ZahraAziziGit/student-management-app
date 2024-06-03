import 'package:flutter/material.dart';
import 'package:radiohead/theme/theme.dart';
import 'package:radiohead/widgets/custom_scaffold.dart';
import 'package:radiohead/screens/signup_screen.dart';
import 'package:radiohead/screens/home_screen.dart';

class LogInScreen extends StatefulWidget {
  const LogInScreen({super.key});

  @override
  State<LogInScreen> createState() => _LogInScreenState();
}

class _LogInScreenState extends State<LogInScreen> {
  final _formLoginKey = GlobalKey<FormState>();
  String? _username;
  String? _studentId;
  String? _password;
  bool _isPasswordVisible = false;

  String? _validateUsername(String? value) {
    //todo?
    return null;
  }

  String? _validateId(String? value) {
    //todo?
    return null;
  }

  String? _validatePassword(String? value) {
    //todo?
    return null;
  }

  @override
  Widget build(BuildContext context) {
    return CustomScaffold(
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
                  key: _formLoginKey,
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Text(
                        "Log in!",
                        style: TextStyle(
                          fontSize: 35.0,
                          fontWeight: FontWeight.bold,
                          fontFamily: 'Roboto',
                          color: lightColorScheme.secondary,
                        ),
                      ),
                      const SizedBox(
                        height: 50.0,
                      ),
                      TextFormField(
                        decoration: InputDecoration(
                          label: const Text("Student ID/Username"),
                          labelStyle: TextStyle(
                            color: lightColorScheme.secondary,
                            fontSize: 16.0,
                            fontFamily: 'Courier New',
                          ),
                          hintText: "Enter your student ID/Username",
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
                          onPressed: () {
                            if (_formLoginKey.currentState!.validate()) {
                              ScaffoldMessenger.of(context).showSnackBar(
                                const SnackBar(
                                  content: Text("Success!"),
                                ),
                              );
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (e) => const HomeScreen(),
                                ),
                              );
                            }
                          },
                          style: ElevatedButton.styleFrom(
                            backgroundColor: lightColorScheme.primary,
                            foregroundColor: Colors.white,
                          ),
                          child: const Text("LOG IN"),
                        ),
                      ),
                      const SizedBox(
                        height: 30.0,
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          const Text(
                            "New here? ",
                            style: TextStyle(
                              color: Colors.black45,
                            ),
                          ),
                          GestureDetector(
                            child: Text(
                              "Sign up!",
                              style: TextStyle(
                                fontWeight: FontWeight.bold,
                                color: lightColorScheme.primary,
                              ),
                            ),
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (e) => const SignUpScreen(),
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
}
