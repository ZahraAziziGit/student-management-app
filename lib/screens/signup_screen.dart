import 'package:flutter/material.dart';
import 'package:radiohead/theme/theme.dart';
import 'package:radiohead/widgets/custom_scaffold.dart';
import 'package:radiohead/screens/login_screen.dart';

class SignUpScreen extends StatefulWidget {
  const SignUpScreen({super.key});

  @override
  State<SignUpScreen> createState() => _SignUpScreenState();
}

class _SignUpScreenState extends State<SignUpScreen> {
  final _formSignupKey = GlobalKey<FormState>();

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
            flex: 10,
            child: Container(
              margin: const EdgeInsets.all(20.0),
              padding: const EdgeInsets.fromLTRB(30.0, 50.0, 30.0, 10.0),
              decoration: const BoxDecoration(
                color: Colors.white54,
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
                      const Text(
                        "ثبت نام",
                        style: TextStyle(
                          fontSize: 35.0,
                          fontWeight: FontWeight.bold,
                          color: Colors.black,
                        ),
                      ),
                      const SizedBox(
                        height: 30.0,
                      ),
                      TextFormField(
                        validator: (value) {
                        if (value == null || value.isEmpty) {
                          return "نام کاربری نمی تواند خالی باشد";
                        }
                        RegExp regex = RegExp(r'^[a-zA-Z0-9_]+$');
                        if (!regex.hasMatch(value)) {
                          return "فقط از حروف انگلیسی، اعداد و ـ استفاده کنید";
                        }
                        if (value.length < 5) {
                          return "نام کاربری باید حداقل دارای ۵ کارکتر باشد";
                        }
                        return null;
                      },
                        decoration: InputDecoration(
                          label: const Text("نام کاربری", textAlign: TextAlign.center),
                          hintText: "نام کاربری را وارد نمایید",
                          hintStyle: const TextStyle(
                            color: Colors.black26,
                          ),
                          border: OutlineInputBorder(
                            borderSide: const BorderSide(
                              color: Colors.black12,
                            ),
                            borderRadius: BorderRadius.circular(10),
                          ),
                          enabledBorder: OutlineInputBorder(
                            borderSide: const BorderSide(
                              color: Colors.black12, // Default border color
                            ),
                            borderRadius: BorderRadius.circular(10),
                          ),
                        ),
                      ),
                      const SizedBox(
                        height: 25.0,
                      ),
                      TextFormField(
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return "شماره دانشجویی نمی تواند خالی باشد";
                          }
                          if (value.length < 9) {
                            return "شماره دانشجویی شامل ۹ رقم است";
                          }
                          return null;
                        },
                        decoration: InputDecoration(
                          label: const Text("شماره دانشجویی"),
                          hintText: "شماره دانشجویی را وارد نمایید",
                          hintStyle: const TextStyle(
                            color: Colors.black26,
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
                      ),
                      const SizedBox(
                        height: 25.0,
                      ),
                      // password
                      TextFormField(
                        obscureText: true,
                        obscuringCharacter: '*',
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return "رمز عبور نمی تواند خالی باشد";
                          }
                          if (value.length < 8) {
                            return "رمز عبور باید حداقل شامل ۸ کارکتر باشد";
                          }
                          return null;
                        },
                        decoration: InputDecoration(
                          label: const Text("رمز عبور"),
                          hintText: "رمز عبور را وارد نمایید",
                          hintStyle: const TextStyle(
                            color: Colors.black26,
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
                      ),
                      const SizedBox(
                        height: 25.0,
                      ),
                      SizedBox(
                        width: double.infinity,
                        child: ElevatedButton(
                          onPressed: () {
                            if (_formSignupKey.currentState!.validate()) {
                              ScaffoldMessenger.of(context).showSnackBar(
                                const SnackBar(
                                  content: Text("ثبت نام موفقیت آمیز بود"),
                                ),
                              );
                            }
                          },
                          child: const Text('ثبت نام'),
                        ),
                      ),
                      const SizedBox(
                        height: 30.0,
                      ),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          GestureDetector(
                            onTap: () {
                              Navigator.push(
                                context,
                                MaterialPageRoute(
                                  builder: (e) => const LogInScreen(),
                                ),
                              );
                            },
                            child: Text(
                              "وارد شوید",
                              style: TextStyle(
                                fontWeight: FontWeight.bold,
                                color: lightColorScheme.primary,
                              ),
                            ),
                          ),
                          const Text(
                            " حساب دارید؟",
                            style: TextStyle(
                              color: Colors.black45,
                            ),
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
              height: 10,
            ),
          ),
        ],
      ),
    );
  }
}
