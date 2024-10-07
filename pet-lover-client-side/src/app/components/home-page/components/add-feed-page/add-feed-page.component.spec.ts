import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFeedPageComponent } from './add-feed-page.component';

describe('AddFeedPageComponent', () => {
  let component: AddFeedPageComponent;
  let fixture: ComponentFixture<AddFeedPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddFeedPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddFeedPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
