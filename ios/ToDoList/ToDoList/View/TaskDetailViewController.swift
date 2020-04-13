//
//  TaskDetailViewController.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/09.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class TaskDetailViewController: UIViewController {

    @IBOutlet weak var detailContent: UITextView!
    @IBOutlet weak var detailTitle: UITextField!
    
    @IBAction func cancelEditButton(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    var cardDetailData: CardDetailData?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpDetailView()
    }
    
    private func setUpDetailView() {
        detailTitle.text = cardDetailData?.title
        detailContent.text = cardDetailData?.content
    }

}
