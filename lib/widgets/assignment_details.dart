import 'package:flutter/material.dart';

import 'package:file_picker/file_picker.dart';

import 'dart:io';

class AssignmentDetailsDialog extends StatefulWidget {
  final String title;
  final DateTime deadline;
  final String estimatedTime;
  final String description;

  const AssignmentDetailsDialog({
    super.key,
    required this.title,
    required this.deadline,
    required this.estimatedTime,
    required this.description,
  });

  @override
  AssignmentDetailsDialogState createState() => AssignmentDetailsDialogState();
}

class AssignmentDetailsDialogState extends State<AssignmentDetailsDialog> {
  File? _selectedFile;

  Future<void> _pickFile() async {
    FilePickerResult? result = await FilePicker.platform.pickFiles(
      type: FileType.custom,
      allowedExtensions: ['pdf'],
    );

    if (result != null && result.files.single.path != null) {
      setState(() {
        _selectedFile = File(result.files.single.path!);
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    final now = DateTime.now();
    final daysLeft = widget.deadline.difference(now).inDays;
    return Dialog(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(20.0),
      ),
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: SingleChildScrollView(
          child: Column(
            mainAxisSize: MainAxisSize.min,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  const Text(
                    'Details',
                    style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'Georgia',
                        color: Colors.indigo),
                  ),
                  IconButton(
                    icon: const Icon(Icons.close, color: Colors.indigo),
                    onPressed: () {
                      Navigator.of(context).pop();
                    },
                  ),
                ],
              ),
              const SizedBox(height: 10),
              Text('Title: ${widget.title}'),
              const SizedBox(height: 5),
              Text(widget.deadline.difference(now).inMinutes < 0
                  ? 'Deadline is passed'
                  : 'Until deadline: $daysLeft days'),
              const SizedBox(height: 5),
              Text('Estimated time: ${widget.estimatedTime} hours'),
              const SizedBox(height: 5),
              const Text('Mark: 2'),
              const SizedBox(height: 10),
              const Text('Description'),
              const SizedBox(height: 7),
              const TextField(
                maxLines: 1,
                decoration: InputDecoration(
                  hintText: 'page 168',
                  hintStyle: TextStyle(
                      color: Colors.black45, fontWeight: FontWeight.normal),
                  enabledBorder: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(12)),
                    borderSide: BorderSide(color: Colors.black26, width: 1.0),
                  ),
                  focusedBorder: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(12)),
                    borderSide: BorderSide(color: Colors.indigo, width: 1.0),
                  ),
                ),
              ),
              const SizedBox(height: 10),
              Row(
                children: [
                  const Text('Upload: '),
                  const SizedBox(width: 10),
                  Expanded(
                    child: Text(
                      _selectedFile != null
                          ? _selectedFile!.path.split('/').last
                          : 'No file is selected',
                      style: const TextStyle(color: Colors.black54),
                      overflow: TextOverflow.ellipsis,
                    ),
                  ),
                  GestureDetector(
                    onTap: _pickFile,
                    child: const Icon(Icons.upload_file, color: Colors.indigo),
                  ),
                ],
              ),
              const SizedBox(height: 7),
              const TextField(
                maxLines: 1,
                decoration: InputDecoration(
                  hintText: 'Upload details',
                  hintStyle: TextStyle(
                      color: Colors.black45, fontWeight: FontWeight.normal),
                  enabledBorder: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(12)),
                    borderSide: BorderSide(color: Colors.black26, width: 1.0),
                  ),
                  focusedBorder: OutlineInputBorder(
                    borderRadius: BorderRadius.all(Radius.circular(12)),
                    borderSide: BorderSide(color: Colors.indigo, width: 1.0),
                  ),
                ),
              ),
              const SizedBox(height: 20),
              Center(
                child: ElevatedButton(
                  onPressed: () {
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(
                        content: widget.deadline.difference(now).inMinutes > 0
                            ? const Text("Assignment was submitted")
                            : const Text("Too late!"),
                      ),
                    );
                    Navigator.of(context).pop();
                  },
                  child: const Text('Submit'),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
