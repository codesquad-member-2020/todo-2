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
    @IBOutlet weak var authorTextField: UILabel!
    @IBOutlet weak var uploadButton: UIImageView!
    
    @IBAction func cancelEditButton(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
    
    var cardDetailData: CardDetailData?
    var editIndex: Int?
    var editTask: (CardDetailData) -> () = { _ in }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUpDetailView()
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(touchUploadButton))
        uploadButton.addGestureRecognizer(tapGesture)
    }
    
    private func setUpDetailView() {
        detailTitle.text = cardDetailData?.title
        detailContent.text = cardDetailData?.content
    }
    
    @objc func touchUploadButton() {
        cardDetailData?.title = detailTitle.text!
        cardDetailData?.content = detailContent.text!
        editTask(cardDetailData!)
        dismiss(animated: true, completion: nil)
    }

}
