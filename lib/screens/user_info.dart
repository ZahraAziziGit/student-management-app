import 'package:flutter/material.dart';
import 'package:radiohead/theme/theme.dart';
import 'package:radiohead/screens/signup_screen.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:io';

class UserInfoScreen extends StatefulWidget {
  const UserInfoScreen({super.key});

  @override
  State<UserInfoScreen> createState() => _UserInfoScreenState();
}

class _UserInfoScreenState extends State<UserInfoScreen> {
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _confirmedPasswordController = TextEditingController();
  String? _username;
  String? _password;
  String? _confirmedPassword;
  bool _isPasswordVisible = false;
  bool _isConfirmedPasswordVisible = false;
  File? _image;

  @override
  void dispose() {
    _passwordController.dispose();
    super.dispose();
  }

  void _editInformation(BuildContext context) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      builder: (BuildContext context) {
        return GestureDetector(
          child: SingleChildScrollView(
            child: Container(
              padding: EdgeInsets.only(
                  bottom: MediaQuery.of(context).viewInsets.bottom),
              child: Column(
                children: [
                  Padding(
                    padding: const EdgeInsets.fromLTRB(30, 20, 30, 10),
                    child: TextFormField(
                      decoration: const InputDecoration(labelText: "Name"),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(30, 10, 30, 10),
                    child: TextFormField(
                      decoration: const InputDecoration(labelText: "Lastname"),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(30, 10, 30, 10),
                    child: TextFormField(
                      decoration:
                          const InputDecoration(labelText: "Student ID"),
                    ),
                  ),
                  const SizedBox(height: 20),
                  SizedBox(
                    width: 150,
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: lightColorScheme.secondary,
                      ),
                      onPressed: () {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(
                            content: Text("Changes were saved"),
                          ),
                        );
                        Navigator.pop(context);
                      },
                      child: const Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(Icons.check),
                          SizedBox(width: 8),
                          Text("SAVE"),
                        ],
                      ),
                    ),
                  ),
                  const SizedBox(height: 20),
                ],
              ),
            ),
          ),
        );
      },
    );
  }

  void _changePassword(BuildContext context) {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      builder: (BuildContext context) {
        return GestureDetector(
          child: SingleChildScrollView(
            child: Container(
              padding: EdgeInsets.only(
                  bottom: MediaQuery.of(context).viewInsets.bottom),
              child: Column(
                children: [
                  Padding(
                    padding: const EdgeInsets.fromLTRB(30, 20, 30, 10),
                    child: TextFormField(
                      controller: _passwordController,
                      obscureText: !_isPasswordVisible,
                      obscuringCharacter: '*',
                      decoration: InputDecoration(
                        labelText: "New password",
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
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(30, 20, 30, 10),
                    child: TextFormField(
                      controller: _confirmedPasswordController,
                      obscureText: !_isConfirmedPasswordVisible,
                      obscuringCharacter: '*',
                      decoration: InputDecoration(
                        labelText: "Confirmed password",
                        suffixIcon: IconButton(
                          icon: Icon(
                            _isConfirmedPasswordVisible
                                ? Icons.visibility
                                : Icons.visibility_off,
                          ),
                          onPressed: () {
                            setState(() {
                              _isConfirmedPasswordVisible = !_isConfirmedPasswordVisible;
                            });
                          },
                        ),
                      ),
                      validator: _validatePassword,
                      onChanged: (value) {
                        setState(() {
                          _confirmedPassword = value;
                        });
                      },
                    ),
                  ),
                  const SizedBox(height: 20),
                  SizedBox(
                    width: 220,
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: lightColorScheme.secondary,
                      ),
                      onPressed: () {
                        ScaffoldMessenger.of(context).showSnackBar(
                          const SnackBar(
                            content: Text("Password was changed"),
                          ),
                        );
                        Navigator.pop(context);
                      },
                      child: const Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Icon(Icons.key),
                          SizedBox(width: 8),
                          Text("CHANGE PASSWORD"),
                        ],
                      ),
                    ),
                  ),
                  const SizedBox(height: 20),
                ],
              ),
            ),
          ),
        );
      },
    );
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
    RegExp regex = RegExp(r'^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!?*#@]).+$');
    if (!regex.hasMatch(value)) {
      return "Password must contains an uppercase letter, a lowercase letter, a number and a special character (!?*#@)";
    }
    return null;
  }

  Future<void> _pickImage() async {
    final ImagePicker picker = ImagePicker();
    final XFile? image = await picker.pickImage(source: ImageSource.gallery);

    if (image != null) {
      setState(() {
        _image = File(image.path);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        iconTheme: const IconThemeData(color: Colors.white),
        backgroundColor: lightColorScheme.primary,
        elevation: 0,
      ),
      body: Container(
        color: lightColorScheme.primary,
        child: Column(
          children: [
            Container(
              width: double.infinity,
              color: lightColorScheme.primary,
              child: Padding(
                padding: const EdgeInsets.symmetric(vertical: 10.0),
                child: Column(
                  children: [
                    Stack(
                      children: [
                        CircleAvatar(
                          radius: 50,
                          backgroundColor: Colors.white,
                          child: CircleAvatar(
                            radius: 45,
                            backgroundColor: Colors.grey,
                            backgroundImage:
                                _image != null ? FileImage(_image!) : null,
                            child: _image == null
                                ? const Icon(Icons.person, size: 67)
                                : null,
                          ),
                        ),
                        Positioned(
                          bottom: 0,
                          right: 0,
                          child: GestureDetector(
                            onTap: _pickImage, //todo
                            child: const CircleAvatar(
                              radius: 15,
                              backgroundColor: Colors.black87,
                              child: Icon(Icons.camera_alt_outlined, size: 15),
                            ),
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 10),
                    const Text(
                      "Taghi Taghavi",
                      style: TextStyle(
                        fontFamily: 'Georgia',
                        color: Colors.white,
                        fontSize: 23,
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                    const Text(
                      "Student",
                      style: TextStyle(
                        fontFamily: 'Georgia',
                        color: Colors.white54,
                        fontSize: 17,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            Expanded(
              child: Container(
                margin: const EdgeInsets.all(0),
                padding: const EdgeInsets.fromLTRB(20, 20, 20, 10),
                decoration: const BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.only(
                    topLeft: Radius.circular(30.0),
                    topRight: Radius.circular(30.0),
                  ),
                ),
                child: Column(
                  children: [
                    const Card(
                        child: Padding(
                      padding: EdgeInsets.fromLTRB(5, 7, 5, 7),
                      child: Column(
                        children: [
                          SizedBox(
                            height: 45,
                            child: ListTile(
                              title: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                children: [
                                  Text(
                                    "Student ID",
                                    style: TextStyle(
                                      fontFamily: 'Verdana',
                                      fontWeight: FontWeight.bold,
                                    ),
                                    textAlign: TextAlign.left,
                                  ),
                                  Text(
                                    "402243000",
                                    style: TextStyle(
                                      fontFamily: 'Verdana',
                                      color: Colors.grey,
                                    ),
                                    textAlign: TextAlign.right,
                                  ),
                                ],
                              ),
                              contentPadding:
                                  EdgeInsets.symmetric(horizontal: 20),
                            ),
                          ),
                          Divider(
                            endIndent: 20,
                            indent: 20,
                          ),
                          SizedBox(
                            height: 45,
                            child: ListTile(
                              title: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                children: [
                                  Text(
                                    "Current term",
                                    style: TextStyle(
                                      fontFamily: 'Verdana',
                                      fontWeight: FontWeight.bold,
                                    ),
                                    textAlign: TextAlign.left,
                                  ),
                                  Text(
                                    "2024 - Spring",
                                    style: TextStyle(
                                      fontFamily: 'Verdana',
                                      color: Colors.grey,
                                    ),
                                    textAlign: TextAlign.right,
                                  ),
                                ],
                              ),
                              contentPadding:
                                  EdgeInsets.symmetric(horizontal: 20),
                            ),
                          ),
                          Divider(
                            endIndent: 20,
                            indent: 20,
                          ),
                          SizedBox(
                            height: 45,
                            child: ListTile(
                              title: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                children: [
                                  Text(
                                    "Units count",
                                    style: TextStyle(
                                      fontFamily: 'Verdana',
                                      fontWeight: FontWeight.bold,
                                    ),
                                    textAlign: TextAlign.left,
                                  ),
                                  Text(
                                    "16",
                                    style: TextStyle(
                                      fontFamily: 'Verdana',
                                      color: Colors.grey,
                                    ),
                                    textAlign: TextAlign.right,
                                  ),
                                ],
                              ),
                              contentPadding:
                                  EdgeInsets.symmetric(horizontal: 20),
                            ),
                          ),
                          Divider(
                            endIndent: 20,
                            indent: 20,
                          ),
                          SizedBox(
                            height: 45,
                            child: ListTile(
                              title: Row(
                                mainAxisAlignment:
                                    MainAxisAlignment.spaceBetween,
                                children: [
                                  Text(
                                    "Total average",
                                    style: TextStyle(
                                      fontFamily: 'Verdana',
                                      fontWeight: FontWeight.bold,
                                    ),
                                    textAlign: TextAlign.left,
                                  ),
                                  Text(
                                    "16.54",
                                    style: TextStyle(
                                      fontFamily: 'Verdana',
                                      color: Colors.grey,
                                    ),
                                    textAlign: TextAlign.right,
                                  ),
                                ],
                              ),
                              contentPadding:
                                  EdgeInsets.symmetric(horizontal: 20),
                            ),
                          ),
                        ],
                      ),
                    )),
                    const SizedBox(
                      height: 10,
                    ),
                    Card(
                        child: Padding(
                      padding: const EdgeInsets.fromLTRB(0, 5, 5, 5),
                      child: Column(
                        mainAxisSize: MainAxisSize.min,
                        children: [
                          SizedBox(
                            height: 50,
                            child: ListTile(
                              leading: CircleAvatar(
                                radius: 15,
                                backgroundColor: lightColorScheme.secondary,
                                child: const Icon(Icons.edit_rounded,
                                    color: Colors.white, size: 20),
                              ),
                              title: const Text("Edit your profile"),
                              onTap: () {
                                _editInformation(context);
                              },
                            ),
                          ),
                          const Divider(
                            endIndent: 20,
                            indent: 20,
                          ),
                          SizedBox(
                            height: 50,
                            child: ListTile(
                              leading: CircleAvatar(
                                radius: 15,
                                backgroundColor: lightColorScheme.secondary,
                                child: const Icon(Icons.lock_outlined,
                                    color: Colors.white, size: 20),
                              ),
                              title: const Text("Change your password"),
                              onTap: () {
                                _changePassword(context);
                              },
                            ),
                          ),
                        ],
                      ),
                    )),
                    const SizedBox(
                      height: 10,
                    ),
                    SizedBox(
                      width: 360,
                      child: ElevatedButton(
                        onPressed: () {
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text("Account was deleted"),
                            ),
                          );
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (e) => const SignUpScreen(),
                            ),
                          );
                        },
                        style: ElevatedButton.styleFrom(
                          backgroundColor: Colors.red,
                        ),
                        child: const Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Icon(Icons.delete),
                            SizedBox(width: 8),
                            Text("DELETE ACCOUNT"),
                          ],
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
