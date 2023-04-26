import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-delete-confirmation-component',
  templateUrl: './delete-confirmation-component.component.html',
  styleUrls: ['./delete-confirmation-component.component.css'],
})
export class DeleteConfirmationComponentComponent {
  @Output() confirmed = new EventEmitter<boolean>();

  confirmDelete(): void {
    this.confirmed.emit(true);
  }
}
