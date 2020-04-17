//
//  APIClient.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/10.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

class APIClient {
    static let apiClient = APIClient()
    let defaultSession = URLSession(configuration: .default)
    var dataTask: URLSessionDataTask?
    let token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoic2NvdHQiLCJ1c2VySWQiOjEsImV4cCI6MTU4NzE4Nzk3OH0.AGICyVXC3SQWqr9dEFNw7NM2ZPEuPqi9mr_AjTL0kP0"
    
    func requestAllCategoryCard() {
        guard let url = URL(string: "http://15.164.28.20:8080/projects/1") else { return }
        var request = URLRequest(url: url)
        request.setValue(token, forHTTPHeaderField: "Authorization")
        
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            guard let data = data, let responseData = try? JSONDecoder().decode(CardData.self, from: data) else { print("cardDataResponseDataError"); return; }
            
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: .completeLoad, object: nil, userInfo: ["responseData":responseData.data.project])
            }
        }
        dataTask!.resume()
    }
    
    func requestAddNewCard(categoryId: Int, title: String, content: String) {
        guard let url = URL(string: "http://15.164.28.20:8080/projects/1/categories/\(categoryId)/cards") else { return }
        var request = URLRequest(url: url)
        
        let param = ["content": content, "title": title]
        let paramData = try! JSONEncoder().encode(param)
        
        request.httpMethod = "POST"
        request.httpBody = paramData
        request.setValue(token, forHTTPHeaderField: "Authorization")
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            guard let data = data, let responseData = try? JSONDecoder().decode(ResponseValueAddCard.self, from: data) else {
                print("responseDataError"); return; }
            
            if responseData.result == false { return }
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: .reloadData, object: nil, userInfo: nil)
            }
        }
        dataTask!.resume()
    }
    
    func requestDeleteCard(categoryId: Int, cardId: Int) {
        guard let url = URL(string: "http://15.164.28.20:8080/projects/1/categories/\(categoryId)/cards/\(cardId)") else { return }
        var request = URLRequest(url: url)
        
        request.httpMethod = "Delete"
        request.setValue(token, forHTTPHeaderField: "Authorization")
        
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            guard let data = data, let responseData = try? JSONDecoder().decode(ResponseFailureCheck.self, from: data) else {
                print("responseDataError"); return; }
            
            if responseData.result == false { return }
            
            DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                NotificationCenter.default.post(name: .reloadData, object: self, userInfo: nil)
            }
        }
        dataTask!.resume()
    }
    
    func requestEditCard(categoryId: Int, cardId: Int, title: String, content: String) {
        guard let url = URL(string: "http://15.164.28.20:8080/projects/1/categories/\(categoryId)/cards/\(cardId)") else { return }
        var request = URLRequest(url: url)
        
        request.httpMethod = "PUT"
        let param = ["content": content, "title": title]
        let paramData = try! JSONEncoder().encode(param)
        
        request.httpBody = paramData
        request.setValue(token, forHTTPHeaderField: "Authorization")
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            guard let data = data, let responseData = try? JSONDecoder().decode(ResponseFailureCheck.self, from: data) else {
                print("responseDataError"); return; }
            
            if responseData.result == false { return }
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: .reloadData, object: nil, userInfo: nil)
            }
        }
        dataTask!.resume()
    }
    
    func requestMoveCardToDone(destCategoryId: Int, cardId: Int, previousCardId: Int) {
        guard let url = URL(string: "http://15.164.28.20:8080/projects/1/categories/\(destCategoryId)/cards") else { return }
        var request = URLRequest(url: url)
        
        request.httpMethod = "PUT"
        let param = ["cardId": cardId, "previousCardId": previousCardId]
        let paramData = try! JSONEncoder().encode(param)
        
        request.httpBody = paramData
        request.setValue(token, forHTTPHeaderField: "Authorization")
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            guard let data = data, let responseData = try? JSONDecoder().decode(ResponseFailureCheck.self, from: data) else {
                print("responseDataError"); return; }
            
            if responseData.result == false { return }
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: .reloadData, object: nil, userInfo: nil)
            }
        }
        dataTask!.resume()
    }
    
    func requestHistory() {
        guard let url = URL(string: "http://15.164.28.20:8080/projects/1/logs") else { return }
        var request = URLRequest(url: url)
        
        request.httpMethod = "GET"
        request.setValue(token, forHTTPHeaderField: "Authorization")
        
        dataTask = defaultSession.dataTask(with: request) { (data, response, error) in
            if let error = error { print(error); return }
            
            let decoder = JSONDecoder()
            decoder.dateDecodingStrategy = .formatted(.timeDecodingFormatter)
            
            guard let data = data, let responseData = try? decoder.decode(LogData.self, from: data) else {
                print("responseDataError"); return; }
            
            if responseData.result == false { return }
            DispatchQueue.main.async {
                NotificationCenter.default.post(name: .completeHistoryLoad, object: nil, userInfo: ["historyData":responseData.data.projectLogs])
            }
        }
        dataTask!.resume()
    }
}
