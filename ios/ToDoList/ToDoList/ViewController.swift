//
//  ViewController.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/06.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    var toDoViewController: TodoViewController?
    var progressViewController: ProgressViewController?
    var completeViewContrller: CompleteViewController?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "ToDo" {
            toDoViewController = segue.destination as? TodoViewController
        } else if segue.identifier == "Progress" {
            progressViewController = segue.destination as? ProgressViewController
        } else {
            completeViewContrller = segue.destination as? CompleteViewController
        }
    }


}

